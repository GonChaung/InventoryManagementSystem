package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.warehouse.WarehouseCreateDto;
import com.example.InventoryManagementSystem.dto.warehouse.WarehouseResponseDto;
import com.example.InventoryManagementSystem.dto.warehouse.WarehouseUpdateDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.model.Warehouse;
import com.example.InventoryManagementSystem.repository.WarehouseRepository;
import com.example.InventoryManagementSystem.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/warehouses")
@CrossOrigin("*")
public class WarehouseController {

    private final WarehouseService warehouseService;
    private static final Logger log = LoggerFactory.getLogger(WarehouseController.class);

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {this.warehouseService = warehouseService;}

    @PostMapping
    public ResponseEntity<WarehouseResponseDto> createWarehouse(@RequestBody WarehouseCreateDto warehouseCreateDto) {
        if (warehouseCreateDto == null) {
            return ResponseEntity.badRequest().build();
        }

        WarehouseResponseDto warehouseDto = warehouseService.createWarehouse(warehouseCreateDto);
        URI location = UriComponentsBuilder
                .fromUriString("/warehouse/{id}")
                .buildAndExpand(warehouseDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(warehouseDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<WarehouseResponseDto>> getAllWarehouses() {
        try {
            List<WarehouseResponseDto> warehouses = warehouseService.getAllWarehouses();
            if (warehouses.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(warehouses);
        } catch (Exception e) {
            log.error("Error fetching warehouses: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponseDto> getWarehouseById(@PathVariable("id") Long id) {
        try{
            WarehouseResponseDto warehouseDto = warehouseService.getWarehouseById(id);
            return ResponseEntity.ok(warehouseDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseResponseDto> updateWarehouse(@PathVariable("id") Long id, @RequestBody WarehouseUpdateDto warehouseUpdateDto) {
        if (warehouseUpdateDto == null) {
            return ResponseEntity.badRequest().build();
        }
        try{
            WarehouseResponseDto warehouseResponseDto = warehouseService.updateWarehouseById(id, warehouseUpdateDto);
            return ResponseEntity.ok(warehouseResponseDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable("id") Long id) {
        try{
            warehouseService.deleteWarehouseById(id);
            return ResponseEntity.ok("Warehouse with ID" + id + "has been deleted.");
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Error occurred while deleting warehouse.");
        }
    }
}

