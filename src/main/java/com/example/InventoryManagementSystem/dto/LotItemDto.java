package com.example.InventoryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotItemDto {
   private Long id;
   private Long lotId;
   private Long itemId;
   private int quantity;
}
