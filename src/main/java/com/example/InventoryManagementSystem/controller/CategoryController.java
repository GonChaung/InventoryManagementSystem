package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.category.CategoryCreateDTO;
import com.example.InventoryManagementSystem.dto.category.CategoryResponseDTO;
import com.example.InventoryManagementSystem.dto.category.CategoryUpdateDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.service.CategoryService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/categories")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        if (categoryCreateDTO == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if request body is invalid
        }

        CategoryResponseDTO categoryResponseDto = categoryService.createCategory(categoryCreateDTO);
        URI location = UriComponentsBuilder
                .fromUriString("/categories/{id}")
                .buildAndExpand(categoryResponseDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(categoryResponseDto); // Return 201 Created
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        try {
            List<CategoryResponseDTO> categories = categoryService.getAllCategories();
            if (categories.isEmpty()) {
                return ResponseEntity.noContent().build(); // Return 204 if no users
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            log.error("Error fetching users: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        if (categoryUpdateDTO == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if request body is invalid
        }
        try {
            CategoryResponseDTO userResponseDTO = categoryService.updateCatedgoryById(id, categoryUpdateDTO);
            return ResponseEntity.ok(userResponseDTO); // Return 200 if updated successfully
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(null); // Return 404 if employee not found
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Category with ID " + id + " has been deleted."); // Return 200 with success message
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body("Category with ID " + id + " not found."); // Return 404 if not found
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Error occurred while deleting Category."); // Return 500 for other errors
        }
    }


}
