package com.example.InventoryManagementSystem.dto;

import com.example.InventoryManagementSystem.model.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private RoleDTO roleDto;
    private Warehouse warehouseId;
}
