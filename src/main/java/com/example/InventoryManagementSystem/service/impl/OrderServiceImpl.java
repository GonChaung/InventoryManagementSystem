package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.order.OrderCreateDto;
import com.example.InventoryManagementSystem.dto.order.OrderResponseDto;
import com.example.InventoryManagementSystem.dto.order.OrderUpdateDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.OrderMapper;
import com.example.InventoryManagementSystem.model.Order;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.OrderItemRepository;
import com.example.InventoryManagementSystem.repository.OrderRepository;
import com.example.InventoryManagementSystem.repository.ShipmentRepository;
import com.example.InventoryManagementSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ShipmentRepository shipmentRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, ShipmentRepository shipmentRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.shipmentRepository = shipmentRepository;
        this.orderItemRepository = orderItemRepository;
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
        Order order = findOrderById(id); // Ensure order exists
        orderMapper.updateEntityFromDto(orderUpdateDto, order); // Map DTO fields to the existing entity

        int updatedRows = orderRepository.updateOrderById(order);
        if (updatedRows == 0) {
            throw new ResourceNotFoundException("Order with ID " + id + " doesn't exist!");
        }
        return getOrderById(id);
    }

    @Override
    public void softDeleteOrder(Long id) {
        Order order=findOrderById(id);
        order.setStatus(Status.INACTIVE);
        orderRepository.updateOrderById(order);
    }

    @Override
    public void hardDeleteOrder(Long id){
        Order order = findOrderById(id);

        // Delete associated shipments first
        shipmentRepository.deleteByOrderId(id);
        // Delete associated order items
        orderItemRepository.deleteByOrderId(id);
        // Now delete the order itself
        orderRepository.delete(order);
    }

    private Order findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for ID " + id));
    }
}