package com.example.InventoryManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name="permissions")
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends MasterData{
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles=new HashSet<>();
}
