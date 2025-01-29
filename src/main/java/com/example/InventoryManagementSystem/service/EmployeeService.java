package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.EmployeeDto;
import com.example.InventoryManagementSystem.model.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto addEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long id);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto);
    void deleteEmployee(Long id);
}
