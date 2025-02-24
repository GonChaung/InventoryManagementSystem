package com.example.InventoryManagementSystem.model;

public interface ItemInventoryProjection {
    Long getItemId();
    String getItemName();
    Integer getTotalQuantity();
}
