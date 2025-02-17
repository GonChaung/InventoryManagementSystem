package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.lot.LotResponseDTO;
import com.example.InventoryManagementSystem.dto.lot.MasterLotDTO;
import com.example.InventoryManagementSystem.model.Lot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LotMapper extends BaseMapper<Lot, MasterLotDTO>{

    @Override
    @Mapping(source = "warehouseId", target = "warehouse.id")
    @Mapping(source = "categoryId", target = "category.id")
    Lot toEntity(MasterLotDTO masterLotDTO);

    @Override
    @Mapping(source = "warehouse.id", target = "warehouseId", defaultValue = "0L")
    @Mapping(source = "category.id", target = "categoryId", defaultValue = "0L")
    LotResponseDTO toDto(Lot lot);
}
