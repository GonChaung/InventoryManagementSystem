package com.example.InventoryManagementSystem.dto;

import com.example.InventoryManagementSystem.model.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto extends MasterDto{
    private String name;
    private Double price;
    private Long categoryId;
}
