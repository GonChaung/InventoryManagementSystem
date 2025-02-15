package com.example.InventoryManagementSystem.dto.category;

import com.example.InventoryManagementSystem.model.constant.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryResponseDTO extends MasterCategoryDTO{
    private Long id;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdById;
    private Long updatedById;
}
