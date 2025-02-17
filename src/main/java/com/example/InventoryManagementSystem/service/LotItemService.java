package com.example.InventoryManagementSystem.service;


import com.example.InventoryManagementSystem.dto.lotItem.LotItemCreateDTO;
import com.example.InventoryManagementSystem.dto.lotItem.LotItemResponseDTO;
import com.example.InventoryManagementSystem.dto.lotItem.LotItemUpdateDTO;

import java.util.List;

public interface LotItemService {
    LotItemResponseDTO createLotItem(LotItemCreateDTO lotItemCreateDTO);
    LotItemResponseDTO getLotItemById(Long id);
    List<LotItemResponseDTO> getAllLotItems();
    LotItemResponseDTO updateLotItemById(Long id, LotItemUpdateDTO lotItemUpdateDTO);
    void deleteLotItem(Long id);
}
