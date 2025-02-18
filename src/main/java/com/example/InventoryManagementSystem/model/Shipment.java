package com.example.InventoryManagementSystem.model;

import com.example.InventoryManagementSystem.model.constant.ShipmentStatus;
import com.example.InventoryManagementSystem.model.converter.ShipmentStatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipments")
public class Shipment extends MasterData{

    @Column(nullable = false)
    private LocalDateTime shipmentDate;

    @Column(nullable = false)
    @Convert(converter = ShipmentStatusConverter.class)
    private ShipmentStatus shipmentStatus = ShipmentStatus.PENDING;

    @Column
    private String delivery_company;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
