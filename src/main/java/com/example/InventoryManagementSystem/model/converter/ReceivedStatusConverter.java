package com.example.InventoryManagementSystem.model.converter;

import com.example.InventoryManagementSystem.model.constant.ReceivedStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReceivedStatusConverter extends BaseEnumConverter<ReceivedStatus,Integer> {

    public ReceivedStatusConverter() {
        super(ReceivedStatus.class);
    }
}
