package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.category.CategoryCreateDTO;
import com.example.InventoryManagementSystem.dto.category.CategoryResponseDTO;
import com.example.InventoryManagementSystem.dto.category.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO);
    CategoryResponseDTO getCategoryById(Long id);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO updateCatedgoryById(Long id, CategoryUpdateDTO categoryUpdateDTO);
    void deleteCategory(Long id);
}
