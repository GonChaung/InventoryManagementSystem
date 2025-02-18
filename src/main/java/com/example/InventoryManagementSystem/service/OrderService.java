package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.order.OrderCreateDto;
import com.example.InventoryManagementSystem.dto.order.OrderResponseDto;
import com.example.InventoryManagementSystem.dto.order.OrderUpdateDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderCreateDto orderCreateDto);
    OrderResponseDto getOrderById(Long id);
    List<OrderResponseDto> getAllOrders();
    OrderResponseDto updateOrderById(Long id, OrderUpdateDto orderUpdateDto);
    void deleteOrder(Long id);
}
