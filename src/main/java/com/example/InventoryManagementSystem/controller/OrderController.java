package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.OrderDetailsDTO;
import com.example.InventoryManagementSystem.dto.order.OrderCreateDto;
import com.example.InventoryManagementSystem.dto.order.OrderResponseDto;
import com.example.InventoryManagementSystem.dto.order.OrderUpdateDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.service.impl.OrderServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("${api.prefix}/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderServiceImpl orderService;
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        if (orderCreateDto == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }

        try {
            OrderResponseDto orderResponseDto = orderService.createOrder(orderCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto); // 201 Created
        } catch (Exception e) {
            log.error("Error creating order: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getOrderById(id)); // 200 OK
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        try {
            List<OrderResponseDto> orders = orderService.getAllOrders();
            return orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orders); // 204 No Content / 200 OK
        } catch (Exception e) {
            log.error("Error fetching orders: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long id, @RequestBody OrderUpdateDto orderUpdateDto) {
        if (orderUpdateDto == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        try {
            return ResponseEntity.ok(orderService.updateOrderById(id, orderUpdateDto)); // 200 OK
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

    // SOFT DELETE (Set order status to INACTIVE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDeleteOrder(@PathVariable Long id) {
        try {
            orderService.softDeleteOrder(id);
            return ResponseEntity.ok("Order marked as INACTIVE."); // 200 OK
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found."); // 404 Not Found
        }
    }

    // HARD DELETE (Permanently remove the order)
    @DeleteMapping("/{id}/hard")
    public ResponseEntity<String> hardDeleteOrder(@PathVariable Long id) {
        try {
            orderService.hardDeleteOrder(id);
            return ResponseEntity.ok("Order permanently deleted."); // 200 OK
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found."); // 404 Not Found
        }
    }

    @GetMapping("/details")
    public ResponseEntity<List<OrderDetailsDTO>> getOrderDetails() {
        List<OrderDetailsDTO> orderDetails = orderService.getOrderDetails();
        return orderDetails.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orderDetails);
    }
}

