package com.example.InventoryManagementSystem.dto.lotItem;

import lombok.Data;

@Data
public class MasterLotItemDTO {
    private Long lotId;  // Changed to lotId
    private Long itemId;  // Changed to itemId
    private int quantity;
}