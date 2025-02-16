package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.item.ItemCreateDTO;
import com.example.InventoryManagementSystem.dto.item.ItemResponseDTO;
import com.example.InventoryManagementSystem.dto.item.ItemUpdateDTO;

import java.util.List;

public interface LotService {
    ItemResponseDTO createItem(ItemCreateDTO itemCreateDTO);
    ItemResponseDTO getItemById(Long id);
    List<ItemResponseDTO> getAllItems();
    ItemResponseDTO updateItemById(Long id, ItemUpdateDTO itemUpdateDTO);
    void deleteItem(Long id);
}
