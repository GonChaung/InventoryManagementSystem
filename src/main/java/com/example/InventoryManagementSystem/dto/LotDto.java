package com.example.InventoryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotDto {
    private Long id;
    private String location;
    private String status;
    private Long warehouseId;
    private Long categoryId;
}

