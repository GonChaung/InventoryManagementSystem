package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.shipment.MasterShipmentDto;
import com.example.InventoryManagementSystem.dto.shipment.ShipmentResponseDto;
import com.example.InventoryManagementSystem.model.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShipmentMapper extends BaseMapper<Shipment, MasterShipmentDto> {
    @Override
    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "shipmentStatus", target = "shipmentStatus") // Ensure status is mapped
    Shipment toEntity(MasterShipmentDto masterShipmentDto);

    @Override
    @Mapping(source = "order.id", target = "orderId", defaultValue = "0L")
    @Mapping(source = "shipmentStatus", target = "shipmentStatus") // Ensure status is mapped
    ShipmentResponseDto toDto(Shipment shipment);
}

