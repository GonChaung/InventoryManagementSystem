package com.example.InventoryManagementSystem.mapper;
import com.example.InventoryManagementSystem.dto.UserDto;
import com.example.InventoryManagementSystem.dto.RoleDTO;
import com.example.InventoryManagementSystem.model.User;
import com.example.InventoryManagementSystem.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto>{

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
