package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Warehouse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {

    @Query(value = "SELECT * FROM  warehouses", nativeQuery = true)
    List<Warehouse> getAllWarehouses(); //need to check

    @Transactional
    @Query(value = "INSERT INTO warehouses (name,address, status, created_at, updated_at, created_by_id, updated_by_id) " +
            "VALUES (:name, :address,:status, :created_at, :updated_at, :created_by_id, :updated_by_id) " +
            "RETURNING id", nativeQuery = true)
    Integer addWarehouse(
            @Param("name") String name,
            @Param("address") String address,
            @Param("status") Integer status,
            @Param("created_at") LocalDateTime createdAt,
            @Param("updated_at") LocalDateTime updatedAt,
            @Param("created_by_id") Long createdById,
            @Param("updated_by_id") Long updatedById
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE warehouses e SET name = :name, address = :address, updated_at = :updated_aat WHERE id = :id", nativeQuery = true)
    int updateWarehouseById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("address") String address,
            @Param("updated_at") LocalDateTime updatedAt
    );

    @Transactional
    @Query(value = "SELECT * FROM warehouses WHERE id = :id", nativeQuery = true)
    Warehouse getWarehouseById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM warehouses WHERE id = :id", nativeQuery = true)
    int deleteWarehouseById(@Param("id") Long id);

}
