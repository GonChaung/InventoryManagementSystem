package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query(value = "SELECT * FROM categories", nativeQuery = true)
    List<Category> getAllCategories();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categories (name) VALUES (:name)", nativeQuery = true)
    int addCategory(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE categories SET name = :name WHERE id = :id", nativeQuery = true)
    int updateCategoryById(@Param("id") Long id, @Param("name") String name);

    @Query(value = "SELECT * FROM categories WHERE id = :id", nativeQuery = true)
    Category getCategoryById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM categories WHERE id = :id", nativeQuery = true)
    int deleteCategoryById(@Param("id") Long id);

}
