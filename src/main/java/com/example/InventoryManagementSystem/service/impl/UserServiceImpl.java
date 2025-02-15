package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.user.UserCreateDTO;
import com.example.InventoryManagementSystem.dto.user.UserResponseDTO;
import com.example.InventoryManagementSystem.dto.user.UserUpdateDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.UserMapper;
import com.example.InventoryManagementSystem.model.User;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.UserRepository;
import com.example.InventoryManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {
        User user = userMapper.toEntity(userCreateDTO);
        // Use the repository to save the user (assumed to be a method to persist the data)
        Integer id = userRepository.addUser(
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRole().getId(),
                user.getWarehouse().getId(),
                Status.ACTIVE.getValue(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null, null
        );
        return userMapper.toDto(findUserById((long)id));
    }

    @Override
    public UserResponseDTO updateUserById(Long id, UserUpdateDto userDto) {
        User user = userMapper.toEntity(userDto);

        // Update the user details
        int flag = userRepository.updateUserByid(id,
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getWarehouse().getId(),
                user.getEmail(),
                LocalDateTime.now());

        if (flag == 0) {
            throw new ResourceNotFoundException("User with Id " + id + " doesn't exist in database!");
        }

        // Retrieve and return the updated user
        return getUserById(id);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = findUserById(id);  // A helper method for fetching user by ID
        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> result = userRepository.getAllUser();
        if (result == null || result.isEmpty()) {
            return Collections.emptyList(); // Ensure no exceptions occur on empty data
        }
        return result.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteUser(Long id) {
        findUserById(id); // Check if employee exists, throws exception if not
        userRepository.deleteUserById(id);
    }


    // Helper method for finding employee by ID (re-used logic in multiple places)
    private User findUserById(Long id) {
        return Optional.ofNullable(userRepository.getUserById(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + id));
    }
}
