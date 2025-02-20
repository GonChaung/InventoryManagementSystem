package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.customer.SupplierResponseDTO;
import com.example.InventoryManagementSystem.dto.customer.MasterCustomerDTO;
import com.example.InventoryManagementSystem.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends BaseMapper<Customer, MasterCustomerDTO> {

    @Override
    SupplierResponseDTO toDto(Customer customer);
    Customer toEntity(MasterCustomerDTO masterCustomerDTO);
}
