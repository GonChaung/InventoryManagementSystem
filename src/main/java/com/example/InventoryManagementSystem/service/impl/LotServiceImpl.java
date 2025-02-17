package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.lot.LotCreateDTO;
import com.example.InventoryManagementSystem.dto.lot.LotResponseDTO;
import com.example.InventoryManagementSystem.dto.lot.LotUpdateDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.LotMapper;
import com.example.InventoryManagementSystem.model.Lot;
import com.example.InventoryManagementSystem.model.constant.LotStatus;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.LotRepository;
import com.example.InventoryManagementSystem.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LotServiceImpl implements LotService {

    private final LotRepository lotRepository;
    private final LotMapper lotMapper;

    @Autowired
    public LotServiceImpl(LotRepository lotRepository, LotMapper lotMapper) {
        this.lotRepository = lotRepository;
        this.lotMapper = lotMapper;
    }


    @Override
    public LotResponseDTO createLot(LotCreateDTO lotCreateDTO) {
        Lot lot = lotMapper.toEntity(lotCreateDTO);
        Integer id = lotRepository.createLot(
                lot.getLocation(),
                lot.getLotStatus().getValue(),
                lot.getWarehouse().getId(),
                lot.getCategory().getId(),
                Status.ACTIVE.getValue(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null, null
        );
        return lotMapper.toDto(findLotById((long)id));
    }

    @Override
    public LotResponseDTO getLotById(Long id) {
        Lot lot = findLotById(id);
        return lotMapper.toDto(lot);
    }

    @Override
    public List<LotResponseDTO> getAllLots() {
        List<Lot> result = lotRepository.getAllLots();
        if (result == null || result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream()
                .map(lotMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LotResponseDTO updateLotById(Long id, LotUpdateDTO lotUpdateDTO) {
        Lot lot = lotMapper.toEntity(lotUpdateDTO);
        int flag = lotRepository.updateLotById(id,
                lot.getLocation(),
                lot.getWarehouse().getId(),
                lot.getCategory().getId(),
                LotStatus.AVAILABLE.getValue(),
                LocalDateTime.now()
                );

        if (flag == 0){
            throw new ResourceNotFoundException("Lot with ID " + id + " doesn't exist in database!");
        }
        return getLotById(id);
    }

    @Override
    public void deleteLot(Long id) {
        findLotById(id);
        lotRepository.deleteLotById(id);
    }

    private Lot findLotById(Long id){
        return Optional.ofNullable(lotRepository.getLotById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Lot not found for ID " + id ));
    }
}
