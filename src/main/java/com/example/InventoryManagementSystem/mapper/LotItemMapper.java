package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.LotItemDto;
import com.example.InventoryManagementSystem.dto.RoleDTO;
import com.example.InventoryManagementSystem.model.Item;
import com.example.InventoryManagementSystem.model.Lot;
import com.example.InventoryManagementSystem.model.LotItem;
import com.example.InventoryManagementSystem.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface LotItemMapper extends BaseMapper<LotItem, LotItemDto>{
    LotItemMapper INSTANCE = Mappers.getMapper(LotItemMapper.class);
}
