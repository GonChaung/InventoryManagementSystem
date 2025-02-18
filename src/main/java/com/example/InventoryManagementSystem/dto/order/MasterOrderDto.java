package com.example.InventoryManagementSystem.dto.order;

import com.example.InventoryManagementSystem.model.constant.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MasterOrderDto {
    private Double orderDiscount;
    private LocalDateTime orderDate;
    private Double totalCost;
    private OrderStatus orderStatus;
    private Long customerId;
}
