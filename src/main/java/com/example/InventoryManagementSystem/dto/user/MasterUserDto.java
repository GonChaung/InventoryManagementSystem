package com.example.InventoryManagementSystem.dto.user;

import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

@Data
public class MasterUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private Long roleId;
    private Long warehouseId;
}
