package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.item.ItemResponseDTO;
import com.example.InventoryManagementSystem.dto.item.MasterItemDTO;
import com.example.InventoryManagementSystem.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ItemMapper extends BaseMapper<Item, MasterItemDTO> {

    @Override
    Item toEntity(MasterItemDTO masterItemDTO);

    @Override
    @Mapping(target = "categoryId", expression = "java(item.getCategory() != null ? item.getCategory().getId() : null)")
    ItemResponseDTO toDto(Item item);

}