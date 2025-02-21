package com.example.InventoryManagementSystem.model;

import com.example.InventoryManagementSystem.model.constant.PaymentStatus;
import com.example.InventoryManagementSystem.model.constant.ReceivedStatus;
import com.example.InventoryManagementSystem.model.converter.PaymentStatusConverter;
import com.example.InventoryManagementSystem.model.converter.ReceivedStatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplies")
public class Supplies extends MasterData{

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private Double totalCost;

    @Convert(converter = PaymentStatusConverter.class)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedDate;

    @Convert(converter = ReceivedStatusConverter.class)
    @Column(nullable = false)
    private ReceivedStatus receivedStatus;
}
