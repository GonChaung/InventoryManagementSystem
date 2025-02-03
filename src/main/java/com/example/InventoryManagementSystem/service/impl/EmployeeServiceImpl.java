package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.EmployeeDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.EmployeeMapper;
import com.example.InventoryManagementSystem.model.User;
import com.example.InventoryManagementSystem.repository.EmployeeRepository;
import com.example.InventoryManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        User user = employeeMapper.employeeDTOToEmployee(employeeDto);

        // Use the repository to save the user (assumed to be a method to persist the data)
        employeeRepository.addEmployee(
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getNrc(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getRole().getId()
        );

        User addedUser = getEmployeeByNRC(user.getNrc());
        return employeeMapper.employeeToEmployeeDTO(addedUser);
    }

    @Override
    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) {
        User user = employeeMapper.employeeDTOToEmployee(employeeDto);

        // Update the user details
        int flag = employeeRepository.updateEmployeeByid(id,
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
    public EmployeeDto getEmployeeById(Long id) {
        User user = findEmployeeById(id);  // A helper method for fetching user by ID
        return employeeMapper.employeeToEmployeeDTO(user);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<User> result = employeeRepository.getAllEmployee();
        return result.stream()
                .map(employeeMapper::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long id) {
        findEmployeeById(id); // Check if employee exists, throws exception if not
        employeeRepository.deleteEmployeeById(id);
    }

    // Helper method to find employee by NRC (re-used logic in addEmployee)
    private User getEmployeeByNRC(String nrc) {
        return Optional.ofNullable(employeeRepository.getEmployeeByNRC(nrc))
                .orElseThrow(() -> new ResourceNotFoundException("User with NRC " + nrc + " not found!"));
    }

    // Helper method for finding employee by ID (re-used logic in multiple places)
    private User findEmployeeById(Long id) {
        return Optional.ofNullable(employeeRepository.getEmployeeById(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + id));
    }
}
