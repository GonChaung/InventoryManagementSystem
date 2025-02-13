package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.model.Category;
import com.example.InventoryManagementSystem.model.Item;
import com.example.InventoryManagementSystem.dto.ItemDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ItemMapper {
    public ItemDto itemToItemDto(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getPrice(), item.getCategory().getId());
    }

    public Item itemDtoToItem(ItemDto dto) {
        if (dto == null) {
            return null;
        }
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());

        // Set category only if ID is available
        if (dto.getCategoryId() != null) {
            Category category = new Category();
            category.setId(dto.getCategoryId());
            item.setCategory(category);
        }
        return item;
    }
}