package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.lot.LotCreateDTO;
import com.example.InventoryManagementSystem.dto.lot.LotResponseDTO;
import com.example.InventoryManagementSystem.dto.lot.LotUpdateDTO;
import com.example.InventoryManagementSystem.dto.user.UserResponseDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.service.LotService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/lots")
@CrossOrigin("*")
public class LotController {
    private final LotService lotService;
    private static final Logger log = LoggerFactory.getLogger(LotController.class);

    @Autowired
    public LotController(LotService lotService) {
        this.lotService = lotService;
    }

    @PostMapping
    public ResponseEntity<LotResponseDTO> createLot(@RequestBody LotCreateDTO lotCreateDTO) {
        if (lotCreateDTO == null){
            return ResponseEntity.badRequest().build();
        }

        LotResponseDTO lotResponseDTO = lotService.createLot(lotCreateDTO);
        URI location = UriComponentsBuilder
                .fromUriString("/lots/{id}")
                .buildAndExpand(lotResponseDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(lotResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLot(@PathVariable Long id){
        try {
            lotService.deleteLot(id);
            return ResponseEntity.ok("Lot with Id " + id + " has been deleted.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body("Lot with ID " + id + " not found");
        } catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Error occurs in deleting");
        }

    }

    @GetMapping
    public ResponseEntity<List<LotResponseDTO>> getAllLots() {
        try {
            List<LotResponseDTO> lots = lotService.getAllLots();
            if (lots.isEmpty()) {
                return ResponseEntity.noContent().build(); // Return 204 if no users
            }
            return ResponseEntity.ok(lots);
        } catch (Exception e) {
            log.error("Error fetching users: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotResponseDTO> updateLot(@PathVariable Long id, @RequestBody LotUpdateDTO lotUpdateDto) {
        if (lotUpdateDto == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if request body is invalid
        }
        try {
            LotResponseDTO lotResponseDTO = lotService.updateLotById(id, lotUpdateDto);
            return ResponseEntity.ok(lotResponseDTO); // Return 200 if updated successfully
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(null); // Return 404 if employee not found
        }
    }

}
