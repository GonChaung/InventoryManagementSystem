package com.example.InventoryManagementSystem.controller;



import com.example.InventoryManagementSystem.dto.ItemStockDTO;
import com.example.InventoryManagementSystem.dto.item.ItemCreateDTO;
import com.example.InventoryManagementSystem.dto.item.ItemResponseDTO;
import com.example.InventoryManagementSystem.dto.item.ItemUpdateDTO;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.model.ItemInventoryProjection;
import com.example.InventoryManagementSystem.service.impl.ItemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/items")
@CrossOrigin("*")
public class ItemController {

    @Autowired
    private final ItemServiceImpl itemService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> createItem(@RequestBody ItemCreateDTO itemCreateDTO){
        if (itemCreateDTO == null){
            return ResponseEntity.badRequest().build();
        }
        ItemResponseDTO createdItem = itemService.createItem(itemCreateDTO);
        URI location = UriComponentsBuilder
                .fromUriString("/item/{id}")
                .buildAndExpand(createdItem.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdItem);
    }


    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAllItems(){
        try {
            List<ItemResponseDTO> items = itemService.getAllItems();
            if (items.isEmpty()) {
                return ResponseEntity.noContent().build(); // Return 204 if no users
            }
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            log.error("Error fetching items: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long id) {
        try {
            ItemResponseDTO itemDto = itemService.getItemById(id);
            return ResponseEntity.ok(itemDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable Long id, @RequestBody ItemUpdateDTO itemUpdateDto) {
        if (itemUpdateDto == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if request body is invalid
        }

        try {
            ItemResponseDTO itemResponseDto = itemService.updateItemById(id, itemUpdateDto);
            return ResponseEntity.ok(itemResponseDto); // Return 200 if updated successfully
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if item not found
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        try {
            itemService.deleteItem(id);
            return ResponseEntity.ok("Item with id " + id + " has been deleted");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item with ID " + id + " not found ");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting item.");
        }
    }

    @GetMapping("/stock")
    public List<ItemStockDTO> getItemStockDetails() {
        return itemService.getItemStockDetails();
    }

    @GetMapping("/inventory")
    public List<ItemInventoryProjection> getItemInventory() {
        return itemService.getTotalItemQuantities();
    }
}
