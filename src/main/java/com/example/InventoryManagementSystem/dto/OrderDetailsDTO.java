package com.example.InventoryManagementSystem.dto;

import com.example.InventoryManagementSystem.model.constant.OrderStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDetailsDTO {
    private Long id;
    private String name;
    private LocalDateTime orderDate;
    private Double total;
    private OrderStatus status;

    // Constructors
    public OrderDetailsDTO(Long id, String name, String orderDate, Double total, OrderStatus status) {
        this.id = id;
        this.name = name;
        // Convert orderDate String to LocalDateTime
        this.orderDate = LocalDateTime.parse(orderDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.total = total;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
