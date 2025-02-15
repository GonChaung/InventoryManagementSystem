package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.category.CategoryCreateDTO;
import com.example.InventoryManagementSystem.dto.category.CategoryResponseDTO;
import com.example.InventoryManagementSystem.dto.category.CategoryUpdateDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.CategoryMapper;
import com.example.InventoryManagementSystem.model.Category;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.CategoryRepository;
import com.example.InventoryManagementSystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO) {
        Category category = categoryMapper.toEntity(categoryCreateDTO);
        Integer id = categoryRepository.createCategory(
                category.getName(),
                Status.ACTIVE.getValue(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null, null
        );
        return categoryMapper.toDto(findCategoryById((long)id));
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = findCategoryById(id);
        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> result = categoryRepository.getAllCategories();
        if (result == null || result.isEmpty()) {
            return Collections.emptyList(); // Ensure no exceptions occur on empty data
        }
        return result.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO updateCatedgoryById(Long id, CategoryUpdateDTO categoryUpdateDTO) {
        Category category = categoryMapper.toEntity(categoryUpdateDTO);
        int flag = categoryRepository.updateCategoryById(id,
                category.getName(),
                LocalDateTime.now()
        );
        if (flag == 0) {
            throw new ResourceNotFoundException("Category with Id " + id + " doesn't exist in database!");
        }
        return getCategoryById(id);
    }

    @Override
    public void deleteCategory(Long id) {
        findCategoryById(id); // Check if employee exists, throws exception if not
        categoryRepository.deleteCategoryById(id);
    }

    private Category findCategoryById(Long id){
        return Optional.ofNullable(categoryRepository.getCategoryById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for ID " + id));
    }
}
