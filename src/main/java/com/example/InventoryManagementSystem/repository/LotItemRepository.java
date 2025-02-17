package com.example.InventoryManagementSystem.repository;


import com.example.InventoryManagementSystem.model.LotItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LotItemRepository extends CrudRepository<LotItem, Long> {

    @Query(value = "SELECT * FROM lot_items", nativeQuery = true)
    List<LotItem> getAllLotItems();

    @Transactional
    @Query(value = "INSERT INTO lot_items (lot_id, item_id, quantity, status, created_at, updated_at, created_by_id, updated_by_id) " +
            "VALUES (:lot_id, :item_id, :quantity, :status, :created_at, :updated_at, :created_by_id, :updated_by_id)" +
            "RETURNING id", nativeQuery = true)
    int createLotItem(
            @Param("lot_id") Long lotId,
            @Param("item_id") Long itemId,
            @Param("quantity") int quantity,
            @Param("status") Integer status,
            @Param("created_at") LocalDateTime createdAt,
            @Param("updated_at") LocalDateTime updatedAt,
            @Param("created_by_id") Long createdById,
            @Param("updated_by_id") Long updatedById
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE lot_items SET lot_id = :lot_id, item_id = :item_id, quantity = :quantity, " +
            "updated_at = :updated_at WHERE id = :id", nativeQuery = true)
    int updateLotItemById(
            @Param("id") Long id,
            @Param("lot_id") Long lotId,
            @Param("item_id") Long itemId,
            @Param("quantity") int quantity,
            @Param("updated_at") LocalDateTime updatedAt
    );

    @Query(value = "SELECT * FROM lot_items WHERE id = :id", nativeQuery = true)
    LotItem getLotItemById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM lot_items WHERE id = :id", nativeQuery = true)
    int deleteLotItemById(@Param("id") Long id);
}


