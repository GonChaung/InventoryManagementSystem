package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.supplies.SuppliesCreateDto;
import com.example.InventoryManagementSystem.dto.supplies.SuppliesResponseDto;
import com.example.InventoryManagementSystem.dto.supplies.SuppliesUpdateDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.SuppliesMapper;
import com.example.InventoryManagementSystem.model.Supplies;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.ItemRepository;
import com.example.InventoryManagementSystem.repository.SupplierRepository;
import com.example.InventoryManagementSystem.repository.SuppliesRepository;
import com.example.InventoryManagementSystem.service.SuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuppliesServiceImpl implements SuppliesService {
    private final SuppliesRepository suppliesRepository;
    private final SupplierRepository supplierRepository;
    private final ItemRepository itemRepository;
    private final SuppliesMapper suppliesMapper;

    @Autowired
    public SuppliesServiceImpl(SuppliesRepository suppliesRepository, SupplierRepository supplierRepository, ItemRepository itemRepository, SuppliesMapper suppliesMapper) {
        this.suppliesRepository = suppliesRepository;
        this.supplierRepository = supplierRepository;
        this.itemRepository = itemRepository;
        this.suppliesMapper = suppliesMapper;
    }

    @Override
    public SuppliesResponseDto createSupplies(SuppliesCreateDto suppliesCreateDTO) {
        Supplies supplies = suppliesMapper.toEntity(suppliesCreateDTO);
        Integer id = suppliesRepository.createSupplies(
                supplies.getQuantity(),
                supplies.getUnitPrice(),
                supplies.getDiscount(),
                supplies.getTotalCost(),
                supplies.getPaymentStatus().getValue(),
                supplies.getOrderDate(),
                (supplies.getReceivedDate() != null) ? supplies.getReceivedDate() : new Date(),
                supplies.getReceivedStatus().getValue(),
                supplies.getSupplier().getId(),
                supplies.getItem().getId(),
                Status.ACTIVE.getValue(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                null
        );

        return suppliesMapper.toDto(findSuppliesById((long)id));
    }

    @Override
    public SuppliesResponseDto getSuppliesById(Long id) {
        Supplies supplies = findSuppliesById(id);
        return suppliesMapper.toDto(supplies);
    }

    private Supplies findSuppliesById(Long id) {
        return Optional.ofNullable(suppliesRepository.getSuppliesById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Failed to get supplies by id " + id + "!"));
    }

    @Override
    public SuppliesResponseDto updateSuppliesById(Long id, SuppliesUpdateDto suppliesUpdateDTO) {
        Supplies supplies = findSuppliesById(id);

        int flag = suppliesRepository.updateSuppliesById(id,
                supplies.getQuantity(),
                supplies.getUnitPrice(),
                supplies.getDiscount(),
                supplies.getTotalCost(),
                supplies.getPaymentStatus().getValue(),
                supplies.getOrderDate(),
                (supplies.getReceivedDate() != null) ? supplies.getReceivedDate() : new Date(),
                supplies.getReceivedStatus().getValue(),
                supplies.getSupplier().getId(),
                supplies.getItem().getId(),
                LocalDateTime.now()
        );
        if (flag == 0) {
            throw new ResourceNotFoundException("Supplies with id " + id + "does not exist in the database!");
        }

        return getSuppliesById(id);
    }

    @Override
    public List<SuppliesResponseDto> getAllSupplies() {
        List<Supplies> result = suppliesRepository.getAllSupplies();
        if (result == null || result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream().map(suppliesMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteSuppliesById(Long id) {
        findSuppliesById(id);
        suppliesRepository.deleteSuppliesById(id);
    }


//    @Override
//    public void softDeleteOrder(Long id) {
//
//    }
//
//    @Override
//    public void hardDeleteOrder(Long id) {
//
//    }

}