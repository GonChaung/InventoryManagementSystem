package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Category;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query(value = "SELECT * FROM categories", nativeQuery = true)
    List<Category> getAllCategories();

    @Transactional
    @Query(value = "INSERT INTO categories (name, status, created_at, updated_at, created_by_id, updated_by_id) " +
            "VALUES (:name, :status, :created_at, :updated_at, :created_by_id, :updated_by_id)" + "RETURNING id", nativeQuery = true)
    int createCategory(@Param("name") String name,
                       @Param("status") Integer status,
                       @Param("created_at")LocalDateTime createdAt,
                       @Param("updated_at") LocalDateTime updatedAt,
                       @Param("created_by_id") Long createdById,
                       @Param("updated_by_id") Long updatedById);

    @Modifying
    @Transactional
    @Query(value = "UPDATE categories SET name = :name, updated_at = :updated_at " +
            "WHERE id = :id", nativeQuery = true)
    int updateCategoryById(@Param("id") Long id,
                           @Param("name") String name,
                           @Param("updated_at")LocalDateTime updated_at);

    @Query(value = "SELECT * FROM categories WHERE id = :id", nativeQuery = true)
    Category getCategoryById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM categories WHERE id = :id", nativeQuery = true)
    int deleteCategoryById(@Param("id") Long id);

}
