package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.model.Employee;
import com.example.InventoryManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }


    public List<Employee> getEmployeesByRoleId(Long roleId) {
        return employeeRepository.findByRoleId(roleId);
    }



}
