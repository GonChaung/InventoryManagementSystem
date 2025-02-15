package com.example.InventoryManagementSystem.mapper;
import com.example.InventoryManagementSystem.dto.user.MasterUserDto;
import com.example.InventoryManagementSystem.dto.user.UserResponseDTO;
import com.example.InventoryManagementSystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, MasterUserDto>{

    @Override
    @Mapping(source = "roleId", target = "role.id")  // Fix: Remove "user."
    @Mapping(source = "warehouseId", target = "warehouse.id")  // Fix: Remove "user."
    User toEntity(MasterUserDto masterUserDto);

    @Mapping(source = "role.id", target = "roleId", defaultValue = "0L")
    @Mapping(source = "warehouse.id", target = "warehouseId", defaultValue = "0L")
    UserResponseDTO toDto(User user);
}
