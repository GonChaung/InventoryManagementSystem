package com.example.InventoryManagementSystem.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShipmentStatus implements BaseEnum<Integer>{
    PENDING(1),
    DELIVER(3),
    PROCESSING(2);

    private final int value;

    @Override
    public Integer getValue() {
        return value;
    }
}
