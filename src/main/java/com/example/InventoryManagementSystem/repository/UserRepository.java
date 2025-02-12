package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.User;
import com.example.InventoryManagementSystem.model.Warehouse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> getAllUser();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (first_name, last_name, password, address, phone_number, role_id, warehouse_id) " +
            "VALUES (:first_name, :last_name, :password, :address, :phone_number, :role_id, :warehouse_id)", nativeQuery = true)
    int addUser(
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("password") String password,
            @Param("address") String address,
            @Param("phone_number") String phoneNumber,
            @Param("role_id") Long roleId,
            @Param("warehouse_id") Warehouse warehouseId
            );

    @Modifying
    @Transactional
    @Query(value = "UPDATE users e SET first_name = :first_name, last_name = :last_name, password = :password, address = :address, phone_number = :phone_number, warehouse_id = :warehouse_id WHERE id = :id", nativeQuery = true)
    int updateUserByid(@Param("id") Long id,
                           @Param("first_name") String firstName,
                           @Param("last_name") String lastName,
                           @Param("password") String password,
                           @Param("address") String address,
                           @Param("phone_number") String phoneNumber,
                           @Param("warehouse_id") Warehouse warehouseId);

    @Transactional
    @Query(value = "SELECT * FROM users where id=:id", nativeQuery = true)
    User getUserById(@Param("id") Long id);

    @Transactional
    @Query(value = "SELECT * FROM users WHERE nrc = :nrc", nativeQuery = true)
    User getUserByNRC(@Param("nrc") String nrc);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users WHERE id = :id", nativeQuery = true)
    int deleteUserById(@Param("id") Long id);


}
