package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.LotDto;
import com.example.InventoryManagementSystem.dto.RoleDTO;
import com.example.InventoryManagementSystem.model.Category;
import com.example.InventoryManagementSystem.model.Lot;
import com.example.InventoryManagementSystem.model.Role;
import com.example.InventoryManagementSystem.model.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface LotMapper extends BaseMapper<Role, RoleDTO>{
    LotMapper INSTANCE = Mappers.getMapper(LotMapper.class);
}
