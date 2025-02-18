package com.example.InventoryManagementSystem.dto.order;

import com.example.InventoryManagementSystem.model.constant.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponseDto extends MasterOrderDto {
    private Long id;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdById;
    private Long updatedById;
}
