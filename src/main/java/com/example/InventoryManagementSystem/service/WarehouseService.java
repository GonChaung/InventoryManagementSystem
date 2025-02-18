package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.warehouse.WarehouseCreateDto;
import com.example.InventoryManagementSystem.dto.warehouse.WarehouseResponseDto;
import com.example.InventoryManagementSystem.dto.warehouse.WarehouseUpdateDto;
import com.example.InventoryManagementSystem.model.Warehouse;

import java.util.List;

public interface WarehouseService {

    WarehouseResponseDto createWarehouse(WarehouseCreateDto warehouseCreateDto);
    WarehouseResponseDto getWarehouseById(Long id);
    WarehouseResponseDto updateWarehouseById(Long id, WarehouseUpdateDto warehouseUpdateDto);
    List<WarehouseResponseDto> getAllWarehouses();
    void deleteWarehouseById(Long id);



}
