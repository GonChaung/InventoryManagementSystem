package com.example.InventoryManagementSystem.dto.item;

import com.example.InventoryManagementSystem.model.constant.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemResponseDTO extends MasterItemDTO {
    private Long id;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdById;
    private Long updatedById;
    private int quantity;
}
