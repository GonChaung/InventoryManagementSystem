package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.UserDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/employees")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> addEmployee(@RequestBody UserDto empDto) {
        if (empDto == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if request body is invalid
        }

        UserDto userDto = userService.addUser(empDto);
        URI location = UriComponentsBuilder
                .fromUriString("/employees/{id}")
                .buildAndExpand(userDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(userDto); // Return 201 Created
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllEmployees() {
        List<UserDto> employees = userService.getAllUsers();
        return employees.isEmpty() ?
                ResponseEntity.noContent().build() : // Return 204 if no employees found
                ResponseEntity.ok(employees); // Return 200 with employee list
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getEmployeeById(@PathVariable Long id) {
        try {
            UserDto userDto = userService.getUserById(id);
            return ResponseEntity.ok(userDto); // Return 200 if employee found
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).build(); // Return 404 if not found
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateEmployee(@PathVariable Long id, @RequestBody UserDto userDto) {
        if (userDto == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if request body is invalid
        }

        try {
            userDto = userService.updateUserById(id, userDto);
            return ResponseEntity.ok(userDto); // Return 200 if updated successfully
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(null); // Return 404 if employee not found
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User with ID " + id + " has been deleted."); // Return 200 with success message
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body("User with ID " + id + " not found."); // Return 404 if not found
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Error occurred while deleting employee."); // Return 500 for other errors
        }
    }
}
