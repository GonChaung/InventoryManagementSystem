package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.item.ItemCreateDTO;
import com.example.InventoryManagementSystem.dto.item.ItemResponseDTO;
import com.example.InventoryManagementSystem.dto.item.ItemUpdateDTO;
import com.example.InventoryManagementSystem.service.LotService;

import java.util.List;

public class LotServiceImpl implements LotService {
    @Override
    public ItemResponseDTO createItem(ItemCreateDTO itemCreateDTO) {
        return null;
    }

    @Override
    public ItemResponseDTO getItemById(Long id) {
        return null;
    }

    @Override
    public List<ItemResponseDTO> getAllItems() {
        return List.of();
    }

    @Override
    public ItemResponseDTO updateItemById(Long id, ItemUpdateDTO itemUpdateDTO) {
        return null;
    }

    @Override
    public void deleteItem(Long id) {

    }
}
