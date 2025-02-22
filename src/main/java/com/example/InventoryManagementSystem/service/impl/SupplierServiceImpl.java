package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.supplier.SupplierCreateDTO;
import com.example.InventoryManagementSystem.dto.supplier.SupplierResponseDTO;
import com.example.InventoryManagementSystem.dto.supplier.SupplierUpdateDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.SupplierMapper;
import com.example.InventoryManagementSystem.model.Supplier;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.SupplierRepository;
import com.example.InventoryManagementSystem.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Autowired

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;

    }

    @Override
    public SupplierResponseDTO createSupplier(SupplierCreateDTO supplierCreateDTO) {

        Supplier supplier = supplierMapper.toEntity(supplierCreateDTO);
        Integer id = supplierRepository.createSupplier(
                supplier.getFirstName(),
                supplier.getLastName(),
                supplier.getPhoneNumber(),
                supplier.getEmail(),
                supplier.getAddress(),
                Status.ACTIVE.getValue(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,null
        );
        return supplierMapper.toDto(findSupplierById((long)id));
    }

    private Supplier findSupplierById(long id) {
        return Optional.ofNullable(supplierRepository.getSupplierById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Supplier with id " + id + " not found"));
    }

    @Override
    public SupplierResponseDTO updateSupplierById(Long id, SupplierUpdateDTO supplierDTO) {
        Supplier supplier = supplierMapper.toEntity(supplierDTO);

        int flag = supplierRepository.updateSupplierById(id,
                supplier.getFirstName(),
                supplier.getLastName(),
                supplier.getPhoneNumber(),
                supplier.getEmail(),
                supplier.getAddress(),
                LocalDateTime.now()
        );

        if (flag == 0) {
            throw new ResourceNotFoundException("Supplier with id " + id + " not found iin database!");
        }
        return getSupplierById(id);
    }

    @Override
    public SupplierResponseDTO getSupplierById(Long id) {
        Supplier supplier = findSupplierById(id);
        return supplierMapper.toDto(supplier);
    }

    @Override
    public List<SupplierResponseDTO> getAllSuppliers() {
        List<Supplier> result = supplierRepository.getAllSuppliers();
        if (result == null || result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream()
                .map(supplierMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSupplier(Long id) {
        findSupplierById(id);
        supplierRepository.deleteSupplierById(id);
    }
}
