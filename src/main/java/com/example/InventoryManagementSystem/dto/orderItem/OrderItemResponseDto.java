package com.example.InventoryManagementSystem.dto.orderItem;

import com.example.InventoryManagementSystem.model.constant.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderItemResponseDto extends MasterOrderItemDto {
    private Long id;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdById;
    private Long updatedById;
    private Double totalItemDiscount;
}
