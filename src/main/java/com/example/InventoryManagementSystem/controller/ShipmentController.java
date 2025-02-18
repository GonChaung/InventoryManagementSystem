package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.shipment.ShipmentCreateDto;
import com.example.InventoryManagementSystem.dto.shipment.ShipmentResponseDto;
import com.example.InventoryManagementSystem.dto.shipment.ShipmentUpdateDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.service.ShipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/shipments") // Use your API prefix
@CrossOrigin("*") // Or configure specific origins
public class ShipmentController {

    private final ShipmentService shipmentService;
    private static final Logger log = LoggerFactory.getLogger(ShipmentController.class);

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ResponseEntity<ShipmentResponseDto> createShipment(@RequestBody ShipmentCreateDto shipmentCreateDto) {
        if (shipmentCreateDto == null) {
            return ResponseEntity.badRequest().build();
        }

        ShipmentResponseDto shipmentResponseDto = shipmentService.createShipment(shipmentCreateDto);

        URI location = ServletUriComponentsBuilder // Use ServletUriComponentsBuilder
                .fromCurrentRequestUri() // More robust
                .path("/{id}")
                .buildAndExpand(shipmentResponseDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(shipmentResponseDto);
    }

    @GetMapping("/{id}") // Get by ID
    public ResponseEntity<ShipmentResponseDto> getShipmentById(@PathVariable Long id) {
        try {
            ShipmentResponseDto shipment = shipmentService.getShipmentById(id);
            return ResponseEntity.ok(shipment);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).build(); // 404
        }
    }


    @GetMapping
    public ResponseEntity<List<ShipmentResponseDto>> getAllShipments() {
        try {
            List<ShipmentResponseDto> shipments = shipmentService.getAllShipments();
            if (shipments.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }
            return ResponseEntity.ok(shipments);
        } catch (Exception e) {
            log.error("Error fetching shipments: {}", e.getMessage(), e);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipmentResponseDto> updateShipment(@PathVariable Long id, @RequestBody ShipmentUpdateDto shipmentUpdateDto) {
        if (shipmentUpdateDto == null) {
            return ResponseEntity.badRequest().build(); // 400
        }
        try {
            ShipmentResponseDto updatedShipment = shipmentService.updateShipmentById(id, shipmentUpdateDto);
            return ResponseEntity.ok(updatedShipment);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipment(@PathVariable Long id) {
        try {
            shipmentService.deleteShipment(id);
            return ResponseEntity.ok("Shipment with Id " + id + " has been deleted.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body("Shipment with ID " + id + " not found");
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Error occurs in deleting");
        }
    }
}