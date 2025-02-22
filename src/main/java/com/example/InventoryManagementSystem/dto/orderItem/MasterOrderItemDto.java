package com.example.InventoryManagementSystem.dto.orderItem;

import lombok.Data;

@Data
public class MasterOrderItemDto {
    private Long orderId;
    private Long itemId;
    private Double itemDiscount;
    private int quantity;
    private Double totalItemCost;
}
