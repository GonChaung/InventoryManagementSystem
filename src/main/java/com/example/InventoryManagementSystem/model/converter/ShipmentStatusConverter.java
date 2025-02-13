package com.example.InventoryManagementSystem.model.converter;
import com.example.InventoryManagementSystem.model.constant.ShipmentStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ShipmentStatusConverter extends BaseEnumConverter<ShipmentStatus, Integer>{

    public ShipmentStatusConverter() {
        super(ShipmentStatus.class);
    }
}