package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.warehouse.WarehouseCreateDto;
import com.example.InventoryManagementSystem.dto.warehouse.WarehouseResponseDto;
import com.example.InventoryManagementSystem.dto.warehouse.WarehouseUpdateDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.WarehouseMapper;
import com.example.InventoryManagementSystem.model.Warehouse;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.WarehouseRepository;
import com.example.InventoryManagementSystem.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public WarehouseResponseDto createWarehouse(WarehouseCreateDto warehouseCreateDto){
        Warehouse warehouse = warehouseMapper.toEntity(warehouseCreateDto);
        Integer id = warehouseRepository.addWarehouse(
                warehouse.getName(),
                warehouse.getAddress(),
                Status.ACTIVE.getValue(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null, null
        );
        return warehouseMapper.toDto(findWarehouseById((long) id));
    }

    @Override
    public WarehouseResponseDto getWarehouseById(Long id) {
        Warehouse warehouse = findWarehouseById(id);
        return warehouseMapper.toDto(warehouse);
    }

    @Override
    public WarehouseResponseDto updateWarehouseById(Long id, WarehouseUpdateDto warehouseUpdateDto) {
        Warehouse warehouse = warehouseMapper.toEntity(warehouseUpdateDto);

        int flag = warehouseRepository.updateWarehouseById(id,
                warehouse.getName(),
                warehouse.getAddress(),
                LocalDateTime.now());

        if (flag == 0){
            throw new ResourceNotFoundException("Warehouse with id " + id + " not found in database!");
        }
        return getWarehouseById(id);
    }

    @Override
    public List<WarehouseResponseDto> getAllWarehouses() {
        List<Warehouse> result = warehouseRepository.getAllWarehouses();
        if (result == null || result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream()
                .map(warehouseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteWarehouseById(Long id) {
        findWarehouseById(id);
        warehouseRepository.deleteWarehouseById(id);
    }


    private Warehouse findWarehouseById(long id) {
        return Optional.ofNullable(warehouseRepository.getWarehouseById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse with id " + id + " not found"));
    }

}

