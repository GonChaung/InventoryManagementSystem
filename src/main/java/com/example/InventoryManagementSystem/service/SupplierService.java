package com.example.InventoryManagementSystem.service;

import com.example.InventoryManagementSystem.dto.supplier.SupplierCreateDTO;
import com.example.InventoryManagementSystem.dto.supplier.SupplierResponseDTO;
import com.example.InventoryManagementSystem.dto.supplier.SupplierUpdateDTO;

import java.util.List;

public interface SupplierService {
    SupplierResponseDTO createSupplier(SupplierCreateDTO supplierCreateDTO);
    SupplierResponseDTO updateSupplierById(Long id, SupplierUpdateDTO supplierUpdateDTO);
    SupplierResponseDTO getSupplierById(Long id);
    List<SupplierResponseDTO> getAllSuppliers();
    void deleteSupplier(Long id);
}
