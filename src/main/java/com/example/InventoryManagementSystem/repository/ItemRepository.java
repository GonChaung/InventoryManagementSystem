package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Item;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query(value = "SELECT * FROM items", nativeQuery = true)
    List<Item> getAllItems();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO items (name, price, category_id) VALUES (:name, :price, :category_id)", nativeQuery = true)
    int addItem(
            @Param("name") String name,
            @Param("price") Double price,
            @Param("category_id") Long categoryId
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE items SET name = :name, price = :price, category_id = :category_id WHERE id = :id", nativeQuery = true)
    int updateItemById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("price") Double price,
            @Param("category_id") Long categoryId
    );

    @Query(value = "SELECT * FROM items WHERE id = :id", nativeQuery = true)
    Item getItemById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM items WHERE id = :id", nativeQuery = true)
    int deleteItemById(@Param("id") Long id);
}

