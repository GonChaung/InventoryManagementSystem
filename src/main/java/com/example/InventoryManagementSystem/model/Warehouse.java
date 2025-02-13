package com.example.InventoryManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "warehouses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse extends MasterData{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

}


