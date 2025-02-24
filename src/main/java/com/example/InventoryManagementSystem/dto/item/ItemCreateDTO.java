package com.example.InventoryManagementSystem.dto.item;

import lombok.Data;

@Data
public class ItemCreateDTO extends MasterItemDTO {
    private int quantity;
}
