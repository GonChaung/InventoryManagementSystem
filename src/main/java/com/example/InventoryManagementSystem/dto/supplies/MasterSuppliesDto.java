package com.example.InventoryManagementSystem.dto.supplies;

import com.example.InventoryManagementSystem.model.constant.PaymentStatus;
import com.example.InventoryManagementSystem.model.constant.ReceivedStatus;
import lombok.Data;

import java.util.Date;

@Data
public class MasterSuppliesDto {
    private int quantity;
    private Double unitPrice;
    private Double discount;
    private Double totalCost;
    private PaymentStatus paymentStatus;
    private Date orderDate;
    private Date receivedDate;
    private ReceivedStatus receivedStatus;
    private Long supplierId;
    private Long itemId;
}
