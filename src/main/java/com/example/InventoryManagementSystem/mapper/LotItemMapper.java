package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.lotItem.LotItemResponseDTO;
import com.example.InventoryManagementSystem.dto.lotItem.MasterLotItemDTO;
import com.example.InventoryManagementSystem.model.LotItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LotItemMapper extends BaseMapper<LotItem, MasterLotItemDTO> {

    @Override
    @Mapping(source = "lotId", target = "lot.id")  // Map lotId to lot.id
    @Mapping(source = "itemId", target = "item.id")  // Map itemId to item.id
    LotItem toEntity(MasterLotItemDTO masterLotItemDTO);

    @Override
    @Mapping(source = "lot.id", target = "lotId", defaultValue = "0L")  // Map lot.id to lotId
    @Mapping(source = "item.id", target = "itemId", defaultValue = "0L")  // Map item.id to itemId
    LotItemResponseDTO toDto(LotItem lotItem);
}

