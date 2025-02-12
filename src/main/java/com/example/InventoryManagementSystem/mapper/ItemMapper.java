package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.model.Category;
import com.example.InventoryManagementSystem.model.Item;
import com.example.InventoryManagementSystem.dto.ItemDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ItemMapper {
    public ItemDto toDTO(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getPrice(), item.getCategory().getId());
    }

    public Item toEntity(ItemDto dto, Category category) {
        return new Item(dto.getId(), dto.getName(), dto.getPrice(), category, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }
}