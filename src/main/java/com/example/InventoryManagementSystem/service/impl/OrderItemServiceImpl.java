package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.orderItem.OrderItemCreateDto;
import com.example.InventoryManagementSystem.dto.orderItem.OrderItemResponseDto;
import com.example.InventoryManagementSystem.dto.orderItem.OrderItemUpdateDto;
import com.example.InventoryManagementSystem.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Override
    public OrderItemResponseDto createOrder(OrderItemCreateDto orderItemCreateDto) {
        return null;
    }

    @Override
    public OrderItemResponseDto getOrderById(Long id) {
        return null;
    }

    @Override
    public List<OrderItemResponseDto> getAllOrders() {
        return List.of();
    }

    @Override
    public OrderItemResponseDto updateOrderById(Long id, OrderItemUpdateDto orderItemUpdateDto) {
        return null;
    }

    @Override
    public void softDeleteOrderItem(Long id) {

    }

    @Override
    public void hardDeleteOrderItem(Long id) {

    }
}
