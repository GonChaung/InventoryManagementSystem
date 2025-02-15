package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Item;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query(value = "SELECT * FROM items", nativeQuery = true)
    List<Item> getAllItems();

    @Transactional
    @Query(value = "INSERT INTO items (name, price, category_id, status, created_at, updated_at, created_by_id, updated_by_id) " +
            "VALUES (:name, :price, :category_id, :status, :created_at, :updated_at, :created_by_id, updated_by_id) " +
            "RETURNING id", nativeQuery = true)
    int createItem(
            @Param("name") String name,
            @Param("price") Double price,
            @Param("category_id") Long categoryId,
            @Param("status") Integer status,
            @Param("created_at")LocalDateTime crearted,
            @Param("created_by_id") Long createdByID,
            @Param("updated_by_id") Long updated_by_id
            );

    @Modifying
    @Transactional
    @Query(value = "UPDATE items SET name = :name, price = :price, category_id = :category_id, updated_at = :updated_at WHERE id = :id", nativeQuery = true)
    int updateItemById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("price") Double price,
            @Param("category_id") Long categoryId,
            @Param("updated_at") LocalDateTime updatedAt
    );

    @Query(value = "SELECT * FROM items WHERE id = :id", nativeQuery = true)
    Item getItemById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM items WHERE id = :id", nativeQuery = true)
    int deleteItemById(@Param("id") Long id);
}

