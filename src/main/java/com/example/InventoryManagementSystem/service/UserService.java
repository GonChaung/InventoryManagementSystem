package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.user.UserCreateDTO;
import com.example.InventoryManagementSystem.dto.user.UserResponseDTO;
import com.example.InventoryManagementSystem.dto.user.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserCreateDTO userCreateDTO);
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUserById(Long id, UserUpdateDto userDto);
    void deleteUser(Long id);
}
