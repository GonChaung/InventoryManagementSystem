package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.model.Item;
import com.example.InventoryManagementSystem.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ItemMapper extends BaseMapper<Item, ItemDto>{
}