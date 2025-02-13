package com.example.InventoryManagementSystem.mapper;
import com.example.InventoryManagementSystem.dto.RoleDTO;
import com.example.InventoryManagementSystem.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<Role, RoleDTO> {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
}
