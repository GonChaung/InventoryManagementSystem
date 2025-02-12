package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.LotItemDto;
import com.example.InventoryManagementSystem.model.Item;
import com.example.InventoryManagementSystem.model.Lot;
import com.example.InventoryManagementSystem.model.LotItem;
import org.springframework.stereotype.Component;

@Component
public class LotItemMapper {
    public LotItemDto toDTO(LotItem lotItem) {
        return new LotItemDto(lotItem.getId(), lotItem.getLot().getId(), lotItem.getItem().getId(), lotItem.getQuantity());
    }

    public LotItem toEntity(LotItemDto dto, Lot lot, Item item) {
        return new LotItem(dto.getId(), lot, item, dto.getQuantity());
    }
}
