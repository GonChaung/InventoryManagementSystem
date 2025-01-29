package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.EmployeeDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto empDto) {
        if (empDto == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if request body is invalid
        }

        EmployeeDto employeeDto = employeeService.addEmployee(empDto);
        URI location = UriComponentsBuilder
                .fromUriString("/employees/{id}")
                .buildAndExpand(employeeDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(employeeDto); // Return 201 Created
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return employees.isEmpty() ?
                ResponseEntity.noContent().build() : // Return 204 if no employees found
                ResponseEntity.ok(employees); // Return 200 with employee list
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeDto employeeDto = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employeeDto); // Return 200 if employee found
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).build(); // Return 404 if not found
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        if (employeeDto == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if request body is invalid
        }

        try {
            employeeDto = employeeService.updateEmployeeById(id, employeeDto);
            return ResponseEntity.ok(employeeDto); // Return 200 if updated successfully
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(null); // Return 404 if employee not found
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee with ID " + id + " has been deleted."); // Return 200 with success message
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body("Employee with ID " + id + " not found."); // Return 404 if not found
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Error occurred while deleting employee."); // Return 500 for other errors
        }
    }
}
