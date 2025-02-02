package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.EmployeeDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.EmployeeMapper;
import com.example.InventoryManagementSystem.model.Employee;
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
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDto);

        // Use the repository to save the employee (assumed to be a method to persist the data)
        employeeRepository.addEmployee(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getPassword(),
                employee.getNrc(),
                employee.getAddress(),
                employee.getPhoneNumber(),
                employee.getRole().getId()
        );

        Employee addedEmployee = getEmployeeByNRC(employee.getNrc());
        return employeeMapper.employeeToEmployeeDTO(addedEmployee);
    }

    @Override
    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDto);

        // Update the employee details
        int flag = employeeRepository.updateEmployeeByid(id,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getPassword(),
                employee.getNrc(),
                employee.getAddress(),
                employee.getPhoneNumber());

        if (flag == 0) {
            throw new ResourceNotFoundException("Employee with Id " + id + " doesn't exist in database!");
        }

        // Retrieve and return the updated employee
        return getEmployeeById(id);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = findEmployeeById(id);  // A helper method for fetching employee by ID
        return employeeMapper.employeeToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> result = employeeRepository.getAllEmployee();
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
    private Employee getEmployeeByNRC(String nrc) {
        return Optional.ofNullable(employeeRepository.getEmployeeByNRC(nrc))
                .orElseThrow(() -> new ResourceNotFoundException("Employee with NRC " + nrc + " not found!"));
    }

    // Helper method for finding employee by ID (re-used logic in multiple places)
    private Employee findEmployeeById(Long id) {
        return Optional.ofNullable(employeeRepository.getEmployeeById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for ID: " + id));
    }
}
