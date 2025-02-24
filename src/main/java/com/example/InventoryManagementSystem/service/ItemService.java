package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.item.ItemCreateDTO;
import com.example.InventoryManagementSystem.dto.item.ItemResponseDTO;
import com.example.InventoryManagementSystem.dto.item.ItemUpdateDTO;

import java.util.List;

public interface ItemService {
    List<ItemResponseDTO> getAllItems();
    ItemResponseDTO getItemById(Long id);
    ItemResponseDTO createItem(ItemCreateDTO itemDto, int quantity);
    ItemResponseDTO updateItemById(Long id, ItemUpdateDTO itemDto);
    void deleteItem(Long id);
}
