package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.customer.SupplierCreateDTO;
import com.example.InventoryManagementSystem.dto.customer.SupplierResponseDTO;
import com.example.InventoryManagementSystem.dto.customer.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {
    SupplierResponseDTO createCustomer(SupplierCreateDTO customerCreateDTO);
    SupplierResponseDTO updateCustomerById(Long id, CustomerUpdateDTO customerUpdateDTO);
    SupplierResponseDTO getCustomerById(Long id);
    List<SupplierResponseDTO> getAllCustomers();
    void deleteCustomer(Long id);
}
