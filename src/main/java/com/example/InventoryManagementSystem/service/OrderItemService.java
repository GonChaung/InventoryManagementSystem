package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.orderItem.OrderItemCreateDto;
import com.example.InventoryManagementSystem.dto.orderItem.OrderItemResponseDto;
import com.example.InventoryManagementSystem.dto.orderItem.OrderItemUpdateDto;

import java.util.List;

public interface OrderItemService {
    OrderItemResponseDto createOrderItem(OrderItemCreateDto orderItemCreateDto);
    OrderItemResponseDto getOrderItemById(Long id);
    List<OrderItemResponseDto> getAllOrderItems();
    OrderItemResponseDto updateOrderItemById(Long id, OrderItemUpdateDto orderItemUpdateDto);
    void softDeleteOrderItem(Long id);
    void hardDeleteOrderItem(Long id);
}
