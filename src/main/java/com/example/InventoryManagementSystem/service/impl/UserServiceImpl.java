package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.UserDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.UserMapper;
import com.example.InventoryManagementSystem.model.User;
import com.example.InventoryManagementSystem.repository.UserRepository;
import com.example.InventoryManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        // Use the repository to save the user (assumed to be a method to persist the data)
        userRepository.addUser(
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getRole().getId(),
                user.getWarehouse()
        );
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userDto) {
        User user = userMapper.toEntity(userDto);

        // Update the user details
        int flag = userRepository.updateUserByid(id,
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getWarehouse());

        if (flag == 0) {
            throw new ResourceNotFoundException("User with Id " + id + " doesn't exist in database!");
        }

        // Retrieve and return the updated user
        return getUserById(id);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = findUserById(id);  // A helper method for fetching user by ID
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> result = userRepository.getAllUser();
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
