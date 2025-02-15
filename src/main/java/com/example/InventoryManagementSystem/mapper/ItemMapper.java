package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.item.ItemResponseDTO;
import com.example.InventoryManagementSystem.dto.item.MasterItemDTO;
import com.example.InventoryManagementSystem.model.Item;
import com.example.InventoryManagementSystem.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ItemMapper extends BaseMapper<Item, MasterItemDTO> {

    @Override
    Item toEntity(MasterItemDTO masterItemDTO);

    @Override
    ItemResponseDTO toDto(Item item);

}