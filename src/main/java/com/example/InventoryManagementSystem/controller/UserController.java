package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.user.UserCreateDTO;
import com.example.InventoryManagementSystem.dto.user.UserResponseDTO;
import com.example.InventoryManagementSystem.dto.user.UserUpdateDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        if (userCreateDTO == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if request body is invalid
        }

        UserResponseDTO userDto = userService.createUser(userCreateDTO);
        URI location = UriComponentsBuilder
                .fromUriString("/employees/{id}")
                .buildAndExpand(userDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(userDto); // Return 201 Created
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        try {
            List<UserResponseDTO> employees = userService.getAllUsers();
            if (employees.isEmpty()) {
                return ResponseEntity.noContent().build(); // Return 204 if no users
            }
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            log.error("Error fetching users: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getEmployeeById(@PathVariable Long id) {
        try {
            UserResponseDTO userDto = userService.getUserById(id);
            return ResponseEntity.ok(userDto); // Return 200 if employee found
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).build(); // Return 404 if not found
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateEmployee(@PathVariable Long id, @RequestBody UserUpdateDto userDto) {
        if (userDto == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if request body is invalid
        }
        try {
            UserResponseDTO userResponseDTO = userService.updateUserById(id, userDto);
            return ResponseEntity.ok(userResponseDTO); // Return 200 if updated successfully
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
