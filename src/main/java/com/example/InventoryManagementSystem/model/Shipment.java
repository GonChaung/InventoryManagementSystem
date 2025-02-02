package com.example.InventoryManagementSystem.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date shipmentDate;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}