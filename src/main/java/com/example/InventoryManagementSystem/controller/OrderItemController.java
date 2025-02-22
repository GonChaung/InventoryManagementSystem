package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.orderItem.OrderItemCreateDto;
import com.example.InventoryManagementSystem.dto.orderItem.OrderItemResponseDto;
import com.example.InventoryManagementSystem.dto.orderItem.OrderItemUpdateDto;
import com.example.InventoryManagementSystem.service.OrderItemService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("${api.prefix}/order-items")
@CrossOrigin("*")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private static final Logger log = LoggerFactory.getLogger(OrderItemController.class);

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<OrderItemResponseDto> createOrderItem(@RequestBody OrderItemCreateDto orderItemCreateDto) {
        if (orderItemCreateDto == null) {
            return ResponseEntity.badRequest().build();
        }
        OrderItemResponseDto orderItemResponseDto = orderItemService.createOrderItem(orderItemCreateDto);
        return ResponseEntity.ok(orderItemResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponseDto> getOrderItemById(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getOrderItemById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderItemResponseDto>> getAllOrderItems() {
        List<OrderItemResponseDto> orderItems = orderItemService.getAllOrderItems();
        return orderItems.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orderItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemResponseDto> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemUpdateDto orderItemUpdateDto) {
        return ResponseEntity.ok(orderItemService.updateOrderItemById(id, orderItemUpdateDto));
    }

    // SOFT DELETE (Set order item status to INACTIVE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDeleteOrderItem(@PathVariable Long id) {
        orderItemService.softDeleteOrderItem(id);
        return ResponseEntity.ok("Order item marked as INACTIVE.");
    }

    // HARD DELETE (Permanently remove the order item)
    @DeleteMapping("/{id}/hard")
    public ResponseEntity<String> hardDeleteOrderItem(@PathVariable Long id) {
        orderItemService.hardDeleteOrderItem(id);
        return ResponseEntity.ok("Order item permanently deleted.");
    }
}

