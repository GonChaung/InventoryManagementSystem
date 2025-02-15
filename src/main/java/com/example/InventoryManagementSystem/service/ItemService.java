package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.item.ItemCreateDTO;
import com.example.InventoryManagementSystem.dto.item.ItemResponseDTO;
import com.example.InventoryManagementSystem.dto.item.ItemUpdateDTO;
import com.example.InventoryManagementSystem.dto.user.UserCreateDTO;

import java.util.List;

public interface ItemService {
    List<ItemResponseDTO> getAllItems();
    ItemResponseDTO getItemById(Long id);
    ItemResponseDTO createItem(ItemCreateDTO itemDto);
    ItemResponseDTO updateItemById(Long id, ItemUpdateDTO itemDto);
    void deleteItem(Long id);
}
