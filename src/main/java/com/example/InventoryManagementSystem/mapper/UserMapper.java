package com.example.InventoryManagementSystem.mapper;
import com.example.InventoryManagementSystem.dto.user.MasterUserDto;
import com.example.InventoryManagementSystem.dto.user.UserResponseDTO;
import com.example.InventoryManagementSystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, MasterUserDto>{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Override
    @Mapping(source = "roleId", target = "role.id")  // Fix: Remove "user."
    @Mapping(source = "warehouseId", target = "warehouse.id")  // Fix: Remove "user."
    User toEntity(MasterUserDto masterUserDto);

    @Override
    @Mapping(source = "role.id", target = "roleId")  // Fix: Remove "user."
    @Mapping(source = "warehouse.id", target = "warehouseId")  // Fix: Remove "user."
    UserResponseDTO toDto(User user);

}
