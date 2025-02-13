package com.example.InventoryManagementSystem.model.converter;


import com.example.InventoryManagementSystem.model.constant.Status;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter extends BaseEnumConverter<Status, Integer>{

    public StatusConverter() {
        super(Status.class);
    }
}