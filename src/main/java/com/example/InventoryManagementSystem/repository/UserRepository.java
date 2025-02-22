package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.User;
import com.example.InventoryManagementSystem.model.Warehouse;
import com.example.InventoryManagementSystem.model.constant.Status;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> getAllUser();

    @Transactional
    @Query(value = "INSERT INTO users (first_name, last_name, password, address, phone_number, email, role_id, warehouse_id, status, created_at, updated_at, created_by_id, updated_by_id) " +
            "VALUES (:first_name, :last_name, :password, :address, :phone_number, :email, :role_id, :warehouse_id, :status, :created_at, :updated_at, :created_by_id, :updated_by_id) " +
            "RETURNING id", nativeQuery = true)
    Integer createUser(
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("password") String password,
            @Param("address") String address,
            @Param("phone_number") String phoneNumber,
            @Param("email") String email,
            @Param("role_id") Long roleId,
            @Param("warehouse_id") Long warehouseId,
            @Param("status") Integer status,
            @Param("created_at") LocalDateTime createdAt,
            @Param("updated_at") LocalDateTime updatedAt,
            @Param("created_by_id") Long createdById,
            @Param("updated_by_id") Long updatedById
            );


    @Modifying
    @Transactional
    @Query(value = "UPDATE users e SET first_name = :first_name, last_name = :last_name, password = :password, address = :address, phone_number = :phone_number, role_id = :role_id, warehouse_id = :warehouse_id" +
            ", email = :email, updated_at = :updated_at WHERE id = :id", nativeQuery = true)
    int updateUserByid(@Param("id") Long id,
                       @Param("first_name") String firstName,
                       @Param("last_name") String lastName,
                       @Param("password") String password,
                       @Param("address") String address,
                       @Param("phone_number") String phoneNumber,
                       @Param("role_id") Long roleId,
                       @Param("warehouse_id") Long warehouseId,
                       @Param("email") String email,
                       @Param("updated_at") LocalDateTime updatedAt);

    @Transactional
    @Query(value = "SELECT * FROM users where id=:id", nativeQuery = true)
    User getUserById(@Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users WHERE id = :id", nativeQuery = true)
    int deleteUserById(@Param("id") Long id);


}
