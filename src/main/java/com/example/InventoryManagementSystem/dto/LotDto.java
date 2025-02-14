package com.example.InventoryManagementSystem.dto;

import com.example.InventoryManagementSystem.model.constant.LotStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotDto extends MasterDto{
    private String location;
    private LotStatus lotstatus;
    private Long warehouseId;
    private Long categoryId;
}

