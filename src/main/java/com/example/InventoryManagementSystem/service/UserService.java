package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUserById(Long id, UserDto userDto);
    void deleteUser(Long id);
}
