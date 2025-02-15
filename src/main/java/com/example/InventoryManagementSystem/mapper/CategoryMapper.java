package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.category.CategoryResponseDTO;
import com.example.InventoryManagementSystem.dto.category.MasterCategoryDTO;
import com.example.InventoryManagementSystem.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<Category, MasterCategoryDTO> {

    @Override
    Category toEntity(MasterCategoryDTO masterCategoryDTO);

    @Override
    CategoryResponseDTO toDto(Category category);
}
