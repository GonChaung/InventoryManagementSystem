package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.customer.SupplierCreateDTO;
import com.example.InventoryManagementSystem.dto.customer.SupplierResponseDTO;
import com.example.InventoryManagementSystem.dto.customer.CustomerUpdateDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin("*")
public class CustomerController {

    private final CustomerService customerService;
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createCustomer(@RequestBody SupplierCreateDTO customerCreateDTO) {
        if (customerCreateDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        SupplierResponseDTO customerDTO = customerService.createCustomer(customerCreateDTO);
        URI location = UriComponentsBuilder
                .fromUriString("/customers/{id}")
                .buildAndExpand(customerDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(customerDTO);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAllCustomers() {
        try {
            List<SupplierResponseDTO> customers = customerService.getAllCustomers();
            if (customers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            log.error("Error fetching customers: {}", e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getCustomerById(@PathVariable Long id) {
        try {
            SupplierResponseDTO customerDTO = customerService.getCustomerById(id);
            return ResponseEntity.ok(customerDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateDTO customerUpdateDTO) {
        if (customerUpdateDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        try{
            SupplierResponseDTO customerResponseDTO = customerService.updateCustomerById(id, customerUpdateDTO);
            return ResponseEntity.ok(customerResponseDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        try{
            customerService.deleteCustomer(id);
            return ResponseEntity.ok("Customer with ID " + id  + " deleted successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body("Customer with ID " + id + " not found.");
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Error occurred while deleting customer with ID " + id);
        }
    }




}
