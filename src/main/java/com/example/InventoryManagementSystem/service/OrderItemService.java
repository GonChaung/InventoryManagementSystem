package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.order.OrderCreateDto;
import com.example.InventoryManagementSystem.dto.order.OrderResponseDto;
import com.example.InventoryManagementSystem.dto.order.OrderUpdateDto;
import com.example.InventoryManagementSystem.dto.orderItem.OrderItemCreateDto;
import com.example.InventoryManagementSystem.dto.orderItem.OrderItemResponseDto;
import com.example.InventoryManagementSystem.dto.orderItem.OrderItemUpdateDto;
import com.example.InventoryManagementSystem.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItemResponseDto createOrder(OrderItemCreateDto orderItemCreateDto);
    OrderItemResponseDto getOrderById(Long id);
    List<OrderItemResponseDto> getAllOrders();
    OrderItemResponseDto updateOrderById(Long id, OrderItemUpdateDto orderItemUpdateDto);
    void softDeleteOrderItem(Long id);
    void hardDeleteOrderItem(Long id);
}
