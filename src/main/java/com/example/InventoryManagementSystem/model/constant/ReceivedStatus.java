package com.example.InventoryManagementSystem.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReceivedStatus implements BaseEnum<Integer>{
    PENDING(1),
    RECEIVED(2);
    private final int value;

    @Override
    public Integer getValue() {
        return 0;
    }
}
