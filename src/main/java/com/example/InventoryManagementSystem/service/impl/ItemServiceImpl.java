package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.item.ItemCreateDTO;
import com.example.InventoryManagementSystem.dto.item.ItemResponseDTO;
import com.example.InventoryManagementSystem.dto.item.ItemUpdateDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.ItemMapper;
import com.example.InventoryManagementSystem.model.Category;
import com.example.InventoryManagementSystem.model.Item;
import com.example.InventoryManagementSystem.repository.CategoryRepository;
import com.example.InventoryManagementSystem.repository.ItemRepository;

import com.example.InventoryManagementSystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public List<ItemResponseDTO> getAllItems() {
        return itemRepository.getAllItems().stream().map(itemMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ItemResponseDTO getItemById(Long id) {
        return itemMapper.toDto(itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found")));
    }

    @Override
    public ItemResponseDTO createItem(ItemCreateDTO itemCreatedDTO) {
        Item item = itemMapper.toEntity(itemCreatedDTO);
        if (item.getCategory() == null || item.getCategory().getId() == null) {
            Category category = categoryRepository.findById(itemCreatedDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            item.setCategory(category);
        }

        return itemMapper.toDto(itemRepository.save(item));
    }

    @Override
    public ItemResponseDTO updateItemById(Long id, ItemUpdateDTO itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        int flag = itemRepository.updateItemById(id,
                item.getName(),
                item.getPrice(),
                item.getCategory().getId(),
                item.getUpdatedAt());
        if (flag == 0) {
            throw new ResourceNotFoundException("Item with ID " + id + " not found!");
        }

        return getItemById(id);
    }

    @Override
    public void deleteItem(Long id) {
        findById(id);
        itemRepository.deleteItemById(id);
    }

    private Item findById(Long id) {
        return Optional.ofNullable(itemRepository.getItemById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with ID: " + id));
    }
}
