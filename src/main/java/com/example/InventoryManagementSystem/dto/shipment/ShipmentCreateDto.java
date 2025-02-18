package com.example.InventoryManagementSystem.dto.shipment;

import com.example.InventoryManagementSystem.model.constant.ShipmentStatus;
import lombok.Data;

@Data
public class ShipmentCreateDto extends MasterShipmentDto {
    public ShipmentCreateDto() {
        this.setShipmentStatus(ShipmentStatus.PENDING); // Ensure default value
    }
}
