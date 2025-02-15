package com.example.InventoryManagementSystem.service.impl;

import com.example.InventoryManagementSystem.dto.item.ItemCreateDTO;
import com.example.InventoryManagementSystem.dto.item.ItemResponseDTO;
import com.example.InventoryManagementSystem.dto.item.ItemUpdateDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.mapper.ItemMapper;
import com.example.InventoryManagementSystem.model.Category;
import com.example.InventoryManagementSystem.model.Item;
import com.example.InventoryManagementSystem.model.constant.Status;
import com.example.InventoryManagementSystem.repository.CategoryRepository;
import com.example.InventoryManagementSystem.repository.ItemRepository;

import com.example.InventoryManagementSystem.repository.UserRepository;
import com.example.InventoryManagementSystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;
    private final UserRepository userRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, ItemMapper itemMapper, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.itemMapper = itemMapper;
        this.userRepository = userRepository;
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

        Integer id = itemRepository.createItem(
                item.getName(),
                item.getPrice(),
                item.getCategory().getId(),
                Status.ACTIVE.getValue(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null, null
        );

        return itemMapper.toDto(findItemById((long)id));
    }

    @Override
    public ItemResponseDTO updateItemById(Long id, ItemUpdateDTO itemDto) {
        Item item = itemMapper.toEntity(itemDto);

        if (item.getCategory() == null || item.getCategory().getId() == null) {
            Category category = categoryRepository.findById(itemDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            item.setCategory(category);
        }

        int flag = itemRepository.updateItemById(id,
                item.getName(),
                item.getPrice(),
                item.getCategory().getId(), // Now category is not null
                LocalDateTime.now());

        if (flag == 0) {
            throw new ResourceNotFoundException("Item with ID " + id + " not found!");
        }

        return getItemById(id);
    }


    @Override
    public void deleteItem(Long id) {
        findItemById(id);
        itemRepository.deleteItemById(id);
    }

    private Item findItemById(Long id) {
        return Optional.ofNullable(itemRepository.getItemById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with ID: " + id));
    }
}
