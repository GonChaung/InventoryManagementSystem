package com.example.InventoryManagementSystem.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "supplies")
public class Supplies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Double unitCost;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private Double totalCost;

    @Column(nullable = false)
    private String paymentMethod;


    @Column(nullable = false)
    private String paymentStatus;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private Date receivedDate;


    @Column(nullable = false)
    private String receivedStatus;
}