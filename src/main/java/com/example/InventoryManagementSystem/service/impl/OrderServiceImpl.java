package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.order.OrderCreateDto;
import com.example.InventoryManagementSystem.dto.order.OrderResponseDto;
import com.example.InventoryManagementSystem.dto.order.OrderUpdateDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.OrderMapper;
import com.example.InventoryManagementSystem.model.Order;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.OrderRepository;
import com.example.InventoryManagementSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponseDto createOrder(OrderCreateDto orderCreateDto) {
        Order order = orderMapper.toEntity(orderCreateDto);
        orderRepository.createOrder(
                LocalDateTime.now(),
                order.getOrderDiscount(),
                order.getTotalCost(),
                order.getOrderStatus().getValue(),
                order.getCustomer().getId(),
                Status.ACTIVE.getValue(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                null
        );

        // Fetch latest inserted order
        Order createdOrder = orderRepository.findById(order.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Failed to retrieve newly created order!"));
        return orderMapper.toDto(createdOrder);
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order order = findOrderById(id);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.getAllOrders();
        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto updateOrderById(Long id, OrderUpdateDto orderUpdateDto) {
        findOrderById(id); // Ensure order exists

        int updatedRows = orderRepository.updateOrderById(
                id,
                orderUpdateDto.getOrderDiscount(),
                orderUpdateDto.getTotalCost(),
                orderUpdateDto.getOrderStatus().getValue(),
                orderUpdateDto.getCustomerId(),
                LocalDateTime.now()
        );

        if (updatedRows == 0) {
            throw new ResourceNotFoundException("Order with ID " + id + " doesn't exist!");
        }

        return getOrderById(id);
    }

    @Override
    public void deleteOrder(Long id) {
        findOrderById(id);
        orderRepository.deleteOrderById(id);
    }

    private Order findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for ID " + id));
    }
}