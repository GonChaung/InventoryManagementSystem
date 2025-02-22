package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.supplies.MasterSuppliesDto;
import com.example.InventoryManagementSystem.dto.supplies.SuppliesResponseDto;
import com.example.InventoryManagementSystem.model.Supplies;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SuppliesMapper extends BaseMapper<Supplies, MasterSuppliesDto> {

    @Override
    @Mapping(source = "supplierId", target = "supplier.id")
    @Mapping(source = "itemId", target = "item.id")
    Supplies toEntity(MasterSuppliesDto masterSuppliesDto);

    @Override
    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "receivedDate", target = "receivedDate")
    @Mapping(source = "receivedStatus", target = "receivedStatus")
    SuppliesResponseDto toDto(Supplies supplies);
}
