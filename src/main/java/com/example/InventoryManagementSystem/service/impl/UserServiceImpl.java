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
    public UserDto addEmployee(UserDto userDto) {
        User user = userMapper.employeeDTOToEmployee(userDto);

        // Use the repository to save the user (assumed to be a method to persist the data)
        userRepository.addEmployee(
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getNrc(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getRole().getId()
        );

        User addedUser = getEmployeeByNRC(user.getNrc());
        return userMapper.employeeToEmployeeDTO(addedUser);
    }

    @Override
    public UserDto updateEmployeeById(Long id, UserDto userDto) {
        User user = userMapper.employeeDTOToEmployee(userDto);

        // Update the user details
        int flag = userRepository.updateEmployeeByid(id,
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getNrc(),
                user.getAddress(),
                user.getPhoneNumber());

        if (flag == 0) {
            throw new ResourceNotFoundException("User with Id " + id + " doesn't exist in database!");
        }

        // Retrieve and return the updated user
        return getEmployeeById(id);
    }

    @Override
    public UserDto getEmployeeById(Long id) {
        User user = findEmployeeById(id);  // A helper method for fetching user by ID
        return userMapper.employeeToEmployeeDTO(user);
    }

    @Override
    public List<UserDto> getAllEmployees() {
        List<User> result = userRepository.getAllEmployee();
        return result.stream()
                .map(userMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long id) {
        findEmployeeById(id); // Check if employee exists, throws exception if not
        userRepository.deleteEmployeeById(id);
    }

    // Helper method to find employee by NRC (re-used logic in addEmployee)
    private User getEmployeeByNRC(String nrc) {
        return Optional.ofNullable(userRepository.getEmployeeByNRC(nrc))
                .orElseThrow(() -> new ResourceNotFoundException("User with NRC " + nrc + " not found!"));
    }

    // Helper method for finding employee by ID (re-used logic in multiple places)
    private User findEmployeeById(Long id) {
        return Optional.ofNullable(userRepository.getEmployeeById(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + id));
    }
}
