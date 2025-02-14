package com.example.InventoryManagementSystem.controller;

import com.example.InventoryManagementSystem.dto.ItemDto;
import com.example.InventoryManagementSystem.exception.ResourceNotFoundException;
import com.example.InventoryManagementSystem.service.impl.ItemServiceImpl;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ItemController {

    @Autowired
    private final ItemServiceImpl itemServiceImpl;

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto){
        if (itemDto == null){
            return ResponseEntity.badRequest().build();
        }
        ItemDto createdItem = itemServiceImpl.createItem(itemDto);
        URI location = UriComponentsBuilder
                .fromUriString("/item/{id}")
                .buildAndExpand(createdItem.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdItem);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems(){
        List<ItemDto> items = itemServiceImpl.getAllItems();
        return items.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long id) {
        try {
            ItemDto itemDto = itemServiceImpl.getItemById(id);
            return ResponseEntity.ok(itemDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        if (itemDto == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if request body is invalid
        }

        try {
            itemDto = itemServiceImpl.updateItemById(id, itemDto);
            return ResponseEntity.ok(itemDto); // Return 200 if updated successfully
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if item not found
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        try {
            itemServiceImpl.deleteItem(id);
            return ResponseEntity.ok("Item with id " + id + " has been deleted");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item with ID " + id + " not found ");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting item.");
        }
    }
}
