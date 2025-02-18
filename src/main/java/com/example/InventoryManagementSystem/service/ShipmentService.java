package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.shipment.ShipmentCreateDto;
import com.example.InventoryManagementSystem.dto.shipment.ShipmentResponseDto;
import com.example.InventoryManagementSystem.dto.shipment.ShipmentUpdateDto;

import java.util.List;

public interface ShipmentService {
    List<ShipmentResponseDto> getAllShipments();
    ShipmentResponseDto getShipmentById(Long id);
    ShipmentResponseDto createShipment(ShipmentCreateDto shipmentDto);
    ShipmentResponseDto updateShipmentById(Long id, ShipmentUpdateDto shipmentDto);
    void deleteShipment(Long id);
}
