package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query(value = "SELECT * FROM role WHERE id = :id", nativeQuery = true)
    Role findEmployeeById(@Param("id") Long id);
}
