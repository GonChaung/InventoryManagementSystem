package com.example.InventoryManagementSystem.dto.lot;

import com.example.InventoryManagementSystem.model.constant.LotStatus;
import lombok.Data;

@Data
public class MasterLotDTO {
    private String location;
    private LotStatus lotstatus;
    private Long warehouseId;
    private Long categoryId;
}
