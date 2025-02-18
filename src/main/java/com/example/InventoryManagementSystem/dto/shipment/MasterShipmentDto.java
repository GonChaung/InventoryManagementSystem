package com.example.InventoryManagementSystem.dto.shipment;

import com.example.InventoryManagementSystem.model.constant.ShipmentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MasterShipmentDto {
    private LocalDateTime shipmentDate;
    private ShipmentStatus shipmentStatus = ShipmentStatus.PENDING;
    private String delivery_company;
    private Long orderId;
}
