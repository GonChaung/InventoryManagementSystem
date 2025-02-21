package com.example.InventoryManagementSystem.service;


import com.example.InventoryManagementSystem.dto.supplies.SuppliesCreateDto;
import com.example.InventoryManagementSystem.dto.supplies.SuppliesResponseDto;
import com.example.InventoryManagementSystem.dto.supplies.SuppliesUpdateDto;
import com.example.InventoryManagementSystem.model.Supplies;

import java.util.List;

public interface SuppliesService {
    SuppliesResponseDto createSupplies(SuppliesCreateDto suppliesCreateDTO);
    SuppliesResponseDto getSuppliesById(Long id);
    SuppliesResponseDto updateSuppliesById(Long id, SuppliesUpdateDto suppliesUpdateDTO);
    List<SuppliesResponseDto> getAllSupplies();
    void deleteSuppliesById(Long id);
//    void softDeleteOrder(Long id);
//    void hardDeleteOrder(Long id);
}
