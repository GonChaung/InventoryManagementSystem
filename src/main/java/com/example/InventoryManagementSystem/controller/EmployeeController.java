package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.EmployeeDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.model.Employee;
import com.example.InventoryManagementSystem.response.ApiResponse;
import com.example.InventoryManagementSystem.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/employees")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDto empDto) {
        Employee employee = new Employee();
        employee.setFirstName(empDto.getFirstName());
        employee.setLastName(empDto.getLastName());
        employee.setPassword(empDto.getPassword());
        employee.setNrc(empDto.getNrc());
        employee.setAddress(empDto.getAddress());
        employee.setPhoneNumber(empDto.getPhoneNumber());
        Employee savedEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllAnimals() {
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println(employees.toString());
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        try {
            Employee employee = employeeService.getEmployeeById(id);
            System.out.println(employee.toString());
            return ResponseEntity.ok(employee);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Internal Server Error",null));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee updatedEmployee = null;
        try{
            updatedEmployee = employeeService.updateEmployeeById(id, employee);
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
        return ResponseEntity.ok(new ApiResponse("Updated", updatedEmployee));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee with ID " + id + " has been deleted.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Not Found");
        }
    }
}
