package com.example.InventoryManagementSystem.dto.warehouse;

import com.example.InventoryManagementSystem.model.constant.Status;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class WarehouseResponseDto extends MasterWarehouseDto{

    private Long id;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;

}
