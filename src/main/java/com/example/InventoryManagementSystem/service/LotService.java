package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.lot.LotCreateDTO;
import com.example.InventoryManagementSystem.dto.lot.LotResponseDTO;
import com.example.InventoryManagementSystem.dto.lot.LotUpdateDTO;

import java.util.List;

public interface LotService {
    LotResponseDTO createLot(LotCreateDTO lotCreateDTO);
    LotResponseDTO getLotById(Long id);
    List<LotResponseDTO> getAllLots();
    LotResponseDTO updateLotById(Long id, LotUpdateDTO lotUpdateDTO);
    void deleteLot(Long id);
}
