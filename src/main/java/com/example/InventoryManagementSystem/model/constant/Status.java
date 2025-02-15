package com.example.InventoryManagementSystem.model.constant;

import lombok.Getter;

@Getter
public enum Status implements BaseEnum<Integer>{
    ACTIVE(1),
    INACTIVE(3),
    DELETED(2);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}

