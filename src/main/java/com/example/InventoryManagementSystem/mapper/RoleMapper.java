package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.RoleDto;
import com.example.InventoryManagementSystem.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleDto roleToRoleDTO(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRoleType(role.getRoleType());
        return roleDto;
    }

    public Role roleDtoToRole(RoleDto roleDto){
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setRoleType(roleDto.getRoleType());
        return role;
    }

}
