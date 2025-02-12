package com.example.InventoryManagementSystem.repository;


import com.example.InventoryManagementSystem.model.LotItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LotItemRepository extends CrudRepository<LotItem, Long> {

    @Query(value = "SELECT * FROM lot_items", nativeQuery = true)
    List<LotItem> getAllLotItems();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO lot_items (lot_id, item_id, quantity) VALUES (:lot_id, :item_id, :quantity)", nativeQuery = true)
    int addLotItem(
            @Param("lot_id") Long lotId,
            @Param("item_id") Long itemId,
            @Param("quantity") int quantity
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE lot_items SET lot_id = :lot_id, item_id = :item_id, quantity = :quantity WHERE id = :id", nativeQuery = true)
    int updateLotItemById(
            @Param("id") Long id,
            @Param("lot_id") Long lotId,
            @Param("item_id") Long itemId,
            @Param("quantity") int quantity
    );

    @Query(value = "SELECT * FROM lot_items WHERE id = :id", nativeQuery = true)
    LotItem getLotItemById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM lot_items WHERE id = :id", nativeQuery = true)
    int deleteLotItemById(@Param("id") Long id);
}

