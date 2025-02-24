package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.orderItem.OrderItemCreateDto;
import com.example.InventoryManagementSystem.dto.orderItem.OrderItemResponseDto;
import com.example.InventoryManagementSystem.dto.orderItem.OrderItemUpdateDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.OrderItemMapper;
import com.example.InventoryManagementSystem.model.Item;
import com.example.InventoryManagementSystem.model.Order;
import com.example.InventoryManagementSystem.model.OrderItem;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.ItemRepository;
import com.example.InventoryManagementSystem.repository.OrderItemRepository;
import com.example.InventoryManagementSystem.repository.OrderRepository;
import com.example.InventoryManagementSystem.repository.ShipmentRepository;
import com.example.InventoryManagementSystem.service.OrderItemService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository,
                                OrderItemMapper orderItemMapper,
                                ShipmentRepository shipmentRepository,
                                OrderRepository orderRepository,
                                ItemRepository itemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemMapper = orderItemMapper;
        this.shipmentRepository = shipmentRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public OrderItemResponseDto createOrderItem(OrderItemCreateDto orderItemCreateDto) {
        /*OrderItem orderItem = orderItemMapper.toEntity(orderItemCreateDto);
        orderItemRepository.createOrderItem(
                orderItem.getOrder().getId(),
                orderItem.getItem().getId(),
                orderItem.getItemDiscount(),
                orderItem.getQuantity(),
                orderItem.getTotalItemCost(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        OrderItem createdOrderItem = orderItemRepository.findById(orderItem.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Failed to retrieve newly created order item!"));
        return orderItemMapper.toDto(createdOrderItem);*/

        Order order = orderRepository.findById(orderItemCreateDto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderItemCreateDto.getOrderId()));

        // Fetch the Item entity
        Item item = itemRepository.findById(orderItemCreateDto.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with ID: " + orderItemCreateDto.getItemId()));

        // Convert itemDiscount to percentage
        Double discountPercentage = orderItemCreateDto.getItemDiscount() / 100.0;

        // Calculate total item cost
        Double discountedPrice = item.getPrice() * (1 - discountPercentage);
        Double totalItemCost = discountedPrice * orderItemCreateDto.getQuantity();

        // Create OrderItem entity
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);  // Set fetched Order
        orderItem.setItem(item);
        orderItem.setItemDiscount(orderItemCreateDto.getItemDiscount()); // Keeping original int value
        orderItem.setQuantity(orderItemCreateDto.getQuantity());
        orderItem.setTotalItemCost(totalItemCost);
        orderItem.setCreatedAt(LocalDateTime.now());
        orderItem.setUpdatedAt(LocalDateTime.now());

        // Save OrderItem entity
        orderItem = orderItemRepository.save(orderItem);

        // Convert to response DTO and return
        return orderItemMapper.toDto(orderItem);

    }

    @Override
    public OrderItemResponseDto getOrderItemById(Long id) {
        OrderItem orderItem = findOrderItemById(id);
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public List<OrderItemResponseDto> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.getAllOrderItems();
        return orderItems.stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemResponseDto updateOrderItemById(Long id, OrderItemUpdateDto orderItemUpdateDto) {
        OrderItem orderItem = findOrderItemById(id);
        orderItemMapper.updateEntityFromDto(orderItemUpdateDto, orderItem);

        int updatedRows = orderItemRepository.updateOrderItemById(orderItem);
        if (updatedRows == 0) {
            throw new ResourceNotFoundException("Order item with ID " + id + " doesn't exist!");
        }
        return getOrderItemById(id);
    }

    @Override
    public void softDeleteOrderItem(Long id) {
        OrderItem orderItem = findOrderItemById(id);
        orderItem.setStatus(Status.INACTIVE);
        orderItemRepository.updateOrderItemById(orderItem);
    }

    @Override
    public void hardDeleteOrderItem(Long id) {
        OrderItem orderItem = findOrderItemById(id);

        // Delete related shipments first (if applicable)
        shipmentRepository.deleteShipmentById(id);

        // Now delete the order item itself
        orderItemRepository.delete(orderItem);
    }

    private OrderItem findOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found for ID " + id));
    }
}
