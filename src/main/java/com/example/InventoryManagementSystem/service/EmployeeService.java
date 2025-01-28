package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployeeById(Long id, Employee employee);
    void deleteEmployee(Long id);
}
