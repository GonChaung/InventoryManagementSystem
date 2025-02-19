package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.order.OrderCreateDto;
import com.example.InventoryManagementSystem.dto.order.OrderResponseDto;
import com.example.InventoryManagementSystem.dto.order.OrderUpdateDto;
import com.example.InventoryManagementSystem.service.OrderService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("${api.prefix}/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        if (orderCreateDto == null) {
            return ResponseEntity.badRequest().build();
        }
        OrderResponseDto orderResponseDto = orderService.createOrder(orderCreateDto);
        return ResponseEntity.ok(orderResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long id, @RequestBody OrderUpdateDto orderUpdateDto) {
        return ResponseEntity.ok(orderService.updateOrderById(id, orderUpdateDto));
    }

    // SOFT DELETE (Set order status to INACTIVE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDeleteOrder(@PathVariable Long id) {
        orderService.softDeleteOrder(id);
        return ResponseEntity.ok("Order marked as INACTIVE.");
    }

    // HARD DELETE (Permanently remove the order)
    @DeleteMapping("/{id}/hard")
    public ResponseEntity<String> hardDeleteOrder(@PathVariable Long id) {
        orderService.hardDeleteOrder(id);
        return ResponseEntity.ok("Order permanently deleted.");
    }
}

