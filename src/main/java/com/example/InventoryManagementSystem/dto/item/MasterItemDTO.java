package com.example.InventoryManagementSystem.dto.item;

import lombok.Data;

@Data
public class MasterItemDTO {
    private String name;
    private Double price;
    private Long categoryId;
}
