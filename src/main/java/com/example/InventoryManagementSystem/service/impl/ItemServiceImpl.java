package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.ItemDto;
import com.example.InventoryManagementSystem.mapper.ItemMapper;
import com.example.InventoryManagementSystem.repository.CategoryRepository;
import com.example.InventoryManagementSystem.repository.ItemRepository;
import com.example.InventoryManagementSystem.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.getAllItems().stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemById(Long id) {
        return itemMapper.toDTO(itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found")));
    }

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        return null;
    }

    @Override
    public ItemDto updateItem(Long id, ItemDto itemDto) {
        return null;
    }

    @Override
    public void deleteItem(Long id) {

    }
}
