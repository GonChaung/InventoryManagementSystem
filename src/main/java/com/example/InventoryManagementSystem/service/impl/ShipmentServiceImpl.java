package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.shipment.ShipmentCreateDto;
import com.example.InventoryManagementSystem.dto.shipment.ShipmentResponseDto;
import com.example.InventoryManagementSystem.dto.shipment.ShipmentUpdateDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.ShipmentMapper;
import com.example.InventoryManagementSystem.model.Shipment;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.ShipmentRepository;
import com.example.InventoryManagementSystem.service.ShipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;  // Assuming you have a ShipmentRepository
    private final ShipmentMapper shipmentMapper; // And a ShipmentMapper

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository shipmentRepository, ShipmentMapper shipmentMapper) {
        this.shipmentRepository = shipmentRepository;
        this.shipmentMapper = shipmentMapper;
    }

    @Override
    public ShipmentResponseDto createShipment(ShipmentCreateDto shipmentCreateDto) {
        Shipment shipment = shipmentMapper.toEntity(shipmentCreateDto);

        int id = shipmentRepository.createShipment(
                LocalDateTime.now(),
                shipment.getShipmentStatus().getValue(),
                shipment.getDelivery_company(),
                shipment.getOrder().getId(),
                Status.ACTIVE.getValue(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                null
        );

        // Fetch the created shipment using the returned ID
        Shipment createdShipment = findShipmentById((long) id);
        return shipmentMapper.toDto(createdShipment);
    }

    @Override
    public ShipmentResponseDto getShipmentById(Long id) {
        Shipment shipment = findShipmentById(id);
        return shipmentMapper.toDto(shipment);
    }

    @Override
    public List<ShipmentResponseDto> getAllShipments() {
        List<Shipment> shipments = shipmentRepository.getAllShipments();
        if (shipments == null || shipments.isEmpty()) {
            return Collections.emptyList();
        }
        return shipments.stream()
                .map(shipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShipmentResponseDto updateShipmentById(Long id, ShipmentUpdateDto shipmentUpdateDto) {
        Shipment shipment = shipmentMapper.toEntity(shipmentUpdateDto);

        int updatedRows = shipmentRepository.updateShipmentById(
                id,
                LocalDateTime.now(),
                shipment.getShipmentStatus().getValue(),
                shipment.getDelivery_company(),
                LocalDateTime.now()
        );

        if (updatedRows == 0) {
            throw new ResourceNotFoundException("Shipment with ID " + id + " doesn't exist in database!");
        }

        return getShipmentById(id); // Return the updated shipment
    }

    @Override
    public void deleteShipment(Long id) {
        findShipmentById(id); // Check if exists before deleting
        shipmentRepository.deleteShipmentById(id);
    }

    private Shipment findShipmentById(Long id) {
        return Optional.ofNullable(shipmentRepository.getShipmentById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found for ID " + id));
    }
}