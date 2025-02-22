package com.example.InventoryManagementSystem.controller;


import com.example.InventoryManagementSystem.dto.supplies.SuppliesCreateDto;
import com.example.InventoryManagementSystem.dto.supplies.SuppliesResponseDto;
import com.example.InventoryManagementSystem.dto.supplies.SuppliesUpdateDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.repository.SuppliesRepository;
import com.example.InventoryManagementSystem.service.SuppliesService;
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
@RequestMapping("/api/v1/supplies")
@CrossOrigin("*")
public class SuppliesController {

    private final SuppliesService suppliesService;
    private final Logger logger = LoggerFactory.getLogger(SuppliesController.class);
    private final SuppliesRepository suppliesRepository;

    @Autowired
    public SuppliesController(SuppliesService suppliesService, SuppliesRepository suppliesRepository) {
        this.suppliesService = suppliesService;
        this.suppliesRepository = suppliesRepository;
    }

    @PostMapping
    public ResponseEntity<SuppliesResponseDto> createSupplies(@RequestBody SuppliesCreateDto suppliesCreateDto) {
        if (suppliesCreateDto == null) {
            return ResponseEntity.badRequest().build();
        }

        SuppliesResponseDto suppliesDto = suppliesService.createSupplies(suppliesCreateDto);
        URI location = UriComponentsBuilder
                .fromUriString("/supplies/{id}")
                .buildAndExpand(suppliesDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(suppliesDto);
    }

    @GetMapping
    public ResponseEntity<List<SuppliesResponseDto>> getAllSupplies() {
        try {
            List<SuppliesResponseDto> supplies = suppliesService.getAllSupplies();
            if (supplies.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(supplies);
        } catch (Exception e) {
            logger.error("Error fetching supplies: {}", e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuppliesResponseDto> getSuppliesById(@PathVariable("id") Long id) {
        try{
            SuppliesResponseDto suppliesDto = suppliesService.getSuppliesById(id);
            return ResponseEntity.ok(suppliesDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuppliesResponseDto> updateSupplies(@PathVariable("id") Long id, @RequestBody SuppliesUpdateDto suppliesUpdateDto) {
        if (suppliesUpdateDto == null) {
            return ResponseEntity.badRequest().build();
        }
        try{
            SuppliesResponseDto suppliesResponseDto = suppliesService.updateSuppliesById(id, suppliesUpdateDto);
            return ResponseEntity.ok(suppliesResponseDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSuppliesById(@PathVariable("id") Long id) {
        try{
            suppliesService.deleteSuppliesById(id);
            return ResponseEntity.ok("Supplies with ID " + id + "deleted successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body("Supplies with ID " + id + " not found");
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Error occured while deleting supplies with ID " + id);
        }
    }

}
