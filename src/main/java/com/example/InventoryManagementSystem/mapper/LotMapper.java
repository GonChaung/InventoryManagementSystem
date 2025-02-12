package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.LotDto;
import com.example.InventoryManagementSystem.model.Category;
import com.example.InventoryManagementSystem.model.Lot;
import com.example.InventoryManagementSystem.model.Warehouse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LotMapper {
    public LotDto toDTO(Lot lot) {
        return new LotDto(lot.getId(), lot.getLocation(), lot.getStatus(), lot.getWarehouse().getId(), lot.getCategory().getId());
    }

    public Lot toEntity(LotDto dto, Warehouse warehouse, Category category) {
        return new Lot(dto.getId(), dto.getLocation(), dto.getStatus(), warehouse, category, new ArrayList<>());
    }
}
