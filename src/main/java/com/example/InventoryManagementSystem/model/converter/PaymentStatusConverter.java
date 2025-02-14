package com.example.InventoryManagementSystem.model.converter;

import com.example.InventoryManagementSystem.model.constant.PaymentStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PaymentStatusConverter extends BaseEnumConverter<PaymentStatus, Integer>{

    public PaymentStatusConverter() {
        super(PaymentStatus.class);
    }
}
