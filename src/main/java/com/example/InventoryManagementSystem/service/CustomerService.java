package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.customer.CustomerCreateDTO;
import com.example.InventoryManagementSystem.dto.customer.CustomerResponseDTO;
import com.example.InventoryManagementSystem.dto.customer.CustomerUpdateDTO;
import com.example.InventoryManagementSystem.model.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO);
    CustomerResponseDTO updateCustomerById(Long id, CustomerUpdateDTO customerUpdateDTO);
    CustomerResponseDTO getCustomerById(Long id);
    List<CustomerResponseDTO> getAllCustomers();
    void deleteCustomer(Long id);
}
