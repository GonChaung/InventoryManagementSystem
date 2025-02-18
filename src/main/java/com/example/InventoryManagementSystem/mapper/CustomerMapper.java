package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.customer.CustomerResponseDTO;
import com.example.InventoryManagementSystem.dto.customer.MasterCustomerDTO;
import com.example.InventoryManagementSystem.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends BaseMapper<Customer, MasterCustomerDTO> {

    @Override
    CustomerResponseDTO toDto(Customer customer);
    Customer toEntity(MasterCustomerDTO masterCustomerDTO);
}
