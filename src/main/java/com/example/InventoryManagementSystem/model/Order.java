package com.example.InventoryManagementSystem.model;

import com.example.InventoryManagementSystem.model.constant.OrderStatus;
import com.example.InventoryManagementSystem.model.converter.OrderStatusConverter;
import com.example.InventoryManagementSystem.model.converter.StatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends MasterData{

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private Double orderDiscount;

    @Column(nullable = false)
    private Double totalCost;

    @Column(nullable = false)
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Shipment> shipments;
}
