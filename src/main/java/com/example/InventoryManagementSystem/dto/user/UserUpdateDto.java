package com.example.InventoryManagementSystem.dto.user;

import com.example.InventoryManagementSystem.dto.RoleDTO;
import com.example.InventoryManagementSystem.model.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserUpdateDto extends MasterUserDto {
    private Long id;
}
