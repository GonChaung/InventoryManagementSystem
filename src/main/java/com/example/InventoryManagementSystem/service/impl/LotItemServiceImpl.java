package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.lotItem.LotItemCreateDTO;
import com.example.InventoryManagementSystem.dto.lotItem.LotItemResponseDTO;
import com.example.InventoryManagementSystem.dto.lotItem.LotItemUpdateDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.LotItemMapper;
import com.example.InventoryManagementSystem.model.LotItem;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.LotItemRepository;
import com.example.InventoryManagementSystem.service.LotItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LotItemServiceImpl implements LotItemService {

    private final LotItemRepository lotItemRepository;
    private final LotItemMapper lotItemMapper;

    @Autowired
    public LotItemServiceImpl(LotItemRepository lotItemRepository, LotItemMapper lotItemMapper) {
        this.lotItemRepository = lotItemRepository;
        this.lotItemMapper = lotItemMapper;
    }

    @Override
    public LotItemResponseDTO createLotItem(LotItemCreateDTO lotItemCreateDTO) {
        LotItem lotItem = lotItemMapper.toEntity(lotItemCreateDTO);
        int id = lotItemRepository.createLotItem(
                lotItem.getLot().getId(),
                lotItem.getItem().getId(),
                lotItem.getQuantity(),
                Status.ACTIVE.getValue(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null, null
        );
        return lotItemMapper.toDto(findLotItemById((long) id));
    }

    @Override
    public LotItemResponseDTO getLotItemById(Long id) {
        LotItem lotItem = findLotItemById(id);
        return lotItemMapper.toDto(lotItem);
    }

    @Override
    public List<LotItemResponseDTO> getAllLotItems() {
        List<LotItem> result = lotItemRepository.getAllLotItems();
        if (result == null || result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream().map(lotItemMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public LotItemResponseDTO updateLotItemById(Long id, LotItemUpdateDTO lotItemUpdateDTO) {
        LotItem lotItem = lotItemMapper.toEntity(lotItemUpdateDTO);

        int flag = lotItemRepository.updateLotItemById(
                id,
                lotItem.getLot().getId(),
                lotItem.getItem().getId(),
                lotItem.getQuantity(),// This will now be safe
                LocalDateTime.now()
        );
        if (flag == 0) {
            throw new ResourceNotFoundException("LotItem with Id " + id + " doesn't exist in database!");
        }
        return getLotItemById(id);
    }


    @Override
    public void deleteLotItem(Long id) {
        findLotItemById(id);
        lotItemRepository.deleteLotItemById(id);
    }

    private LotItem findLotItemById(Long id) {
        return Optional.ofNullable(lotItemRepository.getLotItemById(id))
                .orElseThrow(() -> new ResourceNotFoundException("LotItem not found for ID: " + id));
    }
}
