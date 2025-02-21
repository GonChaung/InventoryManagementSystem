package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.supplier.SupplierCreateDTO;
import com.example.InventoryManagementSystem.dto.supplier.SupplierResponseDTO;
import com.example.InventoryManagementSystem.dto.supplier.SupplierUpdateDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.service.SupplierService;
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
@RequestMapping("/api/v1/suppliers")
@CrossOrigin("*")
public class SupplierController {

    private final SupplierService supplierService;
    private static final Logger log = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@RequestBody SupplierCreateDTO supplierCreateDTO) {
        if (supplierCreateDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        SupplierResponseDTO supplierDTO = supplierService.createSupplier(supplierCreateDTO);
        URI location = UriComponentsBuilder
                .fromUriString("/suppliers/{id}")
                .buildAndExpand(supplierDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(supplierDTO);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        try {
            List<SupplierResponseDTO> suppliers = supplierService.getAllSuppliers();
            if (suppliers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(suppliers);
        } catch (Exception e) {
            log.error("Error fetching suppliers: {}", e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable Long id) {
        try {
            SupplierResponseDTO supplierDTO = supplierService.getSupplierById(id);
            return ResponseEntity.ok(supplierDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable Long id, @RequestBody SupplierUpdateDTO supplierUpdateDTO) {
        if (supplierUpdateDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        try{
            SupplierResponseDTO supplierResponseDTO = supplierService.updateSupplierById(id, supplierUpdateDTO);
            return ResponseEntity.ok(supplierResponseDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        try{
            supplierService.deleteSupplier(id);
            return ResponseEntity.ok("Supplier with ID " + id  + " deleted successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body("Supplier with ID " + id + " not found.");
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Error occurred while deleting supplier with ID " + id);
        }
    }




}
