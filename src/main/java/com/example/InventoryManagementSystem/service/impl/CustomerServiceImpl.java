package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.customer.SupplierCreateDTO;
import com.example.InventoryManagementSystem.dto.customer.SupplierResponseDTO;
import com.example.InventoryManagementSystem.dto.customer.CustomerUpdateDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.CustomerMapper;
import com.example.InventoryManagementSystem.model.Customer;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.CustomerRepository;
import com.example.InventoryManagementSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public SupplierResponseDTO createCustomer(SupplierCreateDTO customerCreateDTO) {

        Customer customer = customerMapper.toEntity(customerCreateDTO);
        Integer id = customerRepository.createCustomer(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                Status.ACTIVE.getValue(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,null
        );
        return customerMapper.toDto(findCustomerById((long)id));
    }

    private Customer findCustomerById(long id) {
        return Optional.ofNullable(customerRepository.getCustomerById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found"));
    }

    @Override
    public SupplierResponseDTO updateCustomerById(Long id, CustomerUpdateDTO customerUpdateDTO) {
        Customer customer = customerMapper.toEntity(customerUpdateDTO);

        int flag = customerRepository.updateCustomerById(id,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                LocalDateTime.now()
        );

        if (flag == 0) {
            throw new ResourceNotFoundException("Customer with id " + id + " not found iin database!");
        }
        return getCustomerById(id);
    }

    @Override
    public SupplierResponseDTO getCustomerById(Long id) {
        Customer customer = findCustomerById(id);
        return customerMapper.toDto(customer);
    }

    @Override
    public List<SupplierResponseDTO> getAllCustomers() {
        List<Customer> result = customerRepository.getAllCustomers();
        if (result == null || result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(Long id) {
        findCustomerById(id);
        customerRepository.deleteCustomerById(id);
    }
}
