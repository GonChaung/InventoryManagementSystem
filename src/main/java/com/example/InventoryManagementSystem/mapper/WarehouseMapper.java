package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.warehouse.MasterWarehouseDto;
import com.example.InventoryManagementSystem.dto.warehouse.WarehouseResponseDto;
import com.example.InventoryManagementSystem.model.Warehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper extends BaseMapper<Warehouse, MasterWarehouseDto>{

    @Override
    Warehouse toEntity(MasterWarehouseDto masterWarehouseDto);
    WarehouseResponseDto toDto(Warehouse warehouse);
}
