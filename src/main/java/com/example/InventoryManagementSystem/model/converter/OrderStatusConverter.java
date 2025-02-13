package com.example.InventoryManagementSystem.model.converter;

import com.example.InventoryManagementSystem.model.constant.OrderStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter extends BaseEnumConverter<OrderStatus, Integer>{

    public OrderStatusConverter() {
        super(OrderStatus.class);
    }
}