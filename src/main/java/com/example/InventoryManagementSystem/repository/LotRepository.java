package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Lot;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends CrudRepository<Lot, Long> {

    @Query(value = "SELECT * FROM lots", nativeQuery = true)
    List<Lot> getAllLots();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO lots (location, status, warehouse_id, category_id) VALUES (:location, :status, :warehouse_id, :category_id)", nativeQuery = true)
    int addLot(
            @Param("location") String location,
            @Param("status") String status,
            @Param("warehouse_id") Long warehouseId,
            @Param("category_id") Long categoryId
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE lots SET location = :location, status = :status, warehouse_id = :warehouse_id, category_id = :category_id WHERE id = :id", nativeQuery = true)
    int updateLotById(
            @Param("id") Long id,
            @Param("location") String location,
            @Param("status") String status,
            @Param("warehouse_id") Long warehouseId,
            @Param("category_id") Long categoryId
    );

    @Query(value = "SELECT * FROM lots WHERE id = :id", nativeQuery = true)
    Lot getLotById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM lots WHERE id = :id", nativeQuery = true)
    int deleteLotById(@Param("id") Long id);
}
