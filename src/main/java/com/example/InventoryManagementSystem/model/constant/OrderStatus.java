package com.example.InventoryManagementSystem.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus implements BaseEnum<Integer>{
    SHIPPED(1),
    COMPLETED(3),
    PENDING(2);

    private final int value;

    @Override
    public Integer getValue() {
        return this.value;
    }
}
