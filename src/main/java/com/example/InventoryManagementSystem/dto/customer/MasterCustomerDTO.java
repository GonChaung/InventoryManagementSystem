package com.example.InventoryManagementSystem.dto.customer;

import com.example.InventoryManagementSystem.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class MasterCustomerDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
//    private List<Order> orders;
}
