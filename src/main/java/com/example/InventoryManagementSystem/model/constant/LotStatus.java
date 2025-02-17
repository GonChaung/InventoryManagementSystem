package com.example.InventoryManagementSystem.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LotStatus implements BaseEnum<Integer>{
    AVAILABLE(1),
    UNAVAILABLE(2);

    private final int value;

    @Override
    public Integer getValue() {
        return value;
    }
}
