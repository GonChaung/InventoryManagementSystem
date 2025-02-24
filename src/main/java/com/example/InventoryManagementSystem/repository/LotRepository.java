package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Lot;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LotRepository extends CrudRepository<Lot, Long> {

    @Query(value = "SELECT * FROM lots", nativeQuery = true)
    List<Lot> getAllLots();

    @Transactional
    @Query(value = "INSERT INTO lots (location, warehouse_id, category_id, lot_status, status, created_at, updated_at, created_by_id, updated_by_id ) " +
            "VALUES (:location, :warehouse_id, :category_id, :lot_status,:status, :created_at, :updated_at, :created_by_id, :updated_by_id) " +
            "RETURNING id", nativeQuery = true)
    Integer createLot(
            @Param("location") String location,
            @Param("lot_status") Integer lot_status,
            @Param("warehouse_id") Long warehouseId,
            @Param("category_id") Long categoryId,
            @Param("status") Integer status,
            @Param("created_at") LocalDateTime createdAt,
            @Param("updated_at") LocalDateTime updatedAt,
            @Param("created_by_id") Long createdById,
            @Param("updated_by_id") Long updatedById
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE lots SET location = :location, warehouse_id = :warehouse_id, lot_status = :lot_status, category_id = :category_id, updated_at = :updated_at WHERE id = :id", nativeQuery = true)
    int updateLotById(
            @Param("id") Long id,
            @Param("location") String location,
            @Param("warehouse_id") Long warehouseId,
            @Param("category_id") Long categoryId,
            @Param("lot_status") Integer lotStatus,
            @Param("updated_at") LocalDateTime updatedAt
    );

    @Query(value = "SELECT * FROM lots WHERE id = :id", nativeQuery = true)
    Lot getLotById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM lots WHERE id = :id", nativeQuery = true)
    int deleteLotById(@Param("id") Long id);

    @Query(value = "SELECT * FROM lots WHERE is_default = true LIMIT 1", nativeQuery = true)
    Optional<Lot> findDefaultLot();
}
