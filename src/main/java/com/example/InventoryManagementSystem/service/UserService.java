package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.user.UserCreateDTO;
import com.example.InventoryManagementSystem.dto.user.UserResponseDTO;
import com.example.InventoryManagementSystem.dto.user.UserUpdateDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserCreateDTO userCreateDTO);
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUserById(Long id, UserUpdateDTO userDto);
    void deleteUser(Long id);
}
