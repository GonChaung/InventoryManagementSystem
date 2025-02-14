package com.example.InventoryManagementSystem.model.converter;

import com.example.InventoryManagementSystem.model.constant.LotStatus;
import com.example.InventoryManagementSystem.model.constant.OrderStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LotStatusConverter extends BaseEnumConverter<LotStatus, Integer> {

    public LotStatusConverter() {
        super(LotStatus.class);
    }
}
