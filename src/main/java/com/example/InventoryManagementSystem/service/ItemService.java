package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.ItemDto;

import java.util.List;

public interface ItemService {
    List<ItemDto> getAllItems();
    ItemDto getItemById(Long id);
    ItemDto createItem(ItemDto itemDto);
    ItemDto updateItemById(Long id, ItemDto itemDto);
    void deleteItem(Long id);
}
