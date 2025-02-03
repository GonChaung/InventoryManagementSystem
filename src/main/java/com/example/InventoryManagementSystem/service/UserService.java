package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto addEmployee(UserDto userDto);
    UserDto getEmployeeById(Long id);
    List<UserDto> getAllEmployees();
    UserDto updateEmployeeById(Long id, UserDto userDto);
    void deleteEmployee(Long id);
}
