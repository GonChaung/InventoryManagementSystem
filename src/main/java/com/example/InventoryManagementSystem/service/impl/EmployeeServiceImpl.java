package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.model.Employee;
import com.example.InventoryManagementSystem.repository.EmployeeRepository;
import com.example.InventoryManagementSystem.repository.RoleRepository;
import com.example.InventoryManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Employee addEmployee(Employee employee){
        int flag = employeeRepository.addEmployee(employee.getFirstName(),employee.getLastName(),
                employee.getPassword(), employee.getNrc(),
                employee.getAddress(), employee.getPhoneNumber(), employee.getRole().getId());
        if (flag == 0){
            throw new RuntimeException();
        }
        Employee addedEmployee = employeeRepository.getEmployeeById(employee.getId());
        return addedEmployee;
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee employee){
        int flag = employeeRepository.updateEmployeeByid(id,employee.getFirstName(),employee.getLastName(),employee.getPassword(),employee.getAddress(),employee.getNrc(),
                employee.getPhoneNumber());
        if(flag==0){
            throw new ResourceNotFoundException(" Employee with Id " + id + " doesn't exist in database!");
        }
        Employee updatedEmployee = employeeRepository.getEmployeeById(id);
        return updatedEmployee;
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> result = (List<Employee>) employeeRepository.getAllEmployee();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Employee>();
        }
    }



    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee with id " + id + " does not exist.");
        }else{
            employeeRepository.deleteEmployeeById(id);
        }

    }




}
