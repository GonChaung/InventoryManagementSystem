package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.lotItem.LotItemCreateDTO;
import com.example.InventoryManagementSystem.dto.lotItem.LotItemResponseDTO;
import com.example.InventoryManagementSystem.dto.lotItem.LotItemUpdateDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.service.impl.LotItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/lot_items")
@CrossOrigin("*")
public class LotItemController {

    @Autowired
    private final LotItemServiceImpl lotItemService;
    private static final Logger log = LoggerFactory.getLogger(LotItemController.class);

    public LotItemController(LotItemServiceImpl lotItemService) {
        this.lotItemService = lotItemService;
    }

    @PostMapping
    public ResponseEntity<LotItemResponseDTO> createLotItem(@RequestBody LotItemCreateDTO lotItemCreateDTO) {
        if (lotItemCreateDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        LotItemResponseDTO createdLotItem = lotItemService.createLotItem(lotItemCreateDTO);
        URI location = UriComponentsBuilder
                .fromUriString("/lot_items/{id}")
                .buildAndExpand(createdLotItem.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdLotItem);
    }

    @GetMapping
    public ResponseEntity<List<LotItemResponseDTO>> getAllLotItems() {
        try {
            List<LotItemResponseDTO> lotItems = lotItemService.getAllLotItems();
            if (lotItems.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(lotItems);
        } catch (Exception e) {
            log.error("Error fetching lot items: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotItemResponseDTO> getLotItemById(@PathVariable Long id) {
        try {
            LotItemResponseDTO lotItemDto = lotItemService.getLotItemById(id);
            return ResponseEntity.ok(lotItemDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotItemResponseDTO> updateLotItem(@PathVariable Long id, @RequestBody LotItemUpdateDTO lotItemUpdateDto) {
        if (lotItemUpdateDto == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            LotItemResponseDTO lotItemResponseDto = lotItemService.updateLotItemById(id, lotItemUpdateDto);
            return ResponseEntity.ok(lotItemResponseDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLotItem(@PathVariable Long id) {
        try {
            lotItemService.deleteLotItem(id);
            return ResponseEntity.ok("LotItem with id " + id + " has been deleted");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LotItem with ID " + id + " not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting lot item.");
        }
    }
}
