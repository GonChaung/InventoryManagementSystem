package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customers", nativeQuery = true)
    List<Customer> getAllCustomers();

    @Transactional
    @Query(value = "INSERT INTO customers (first_name, last_name, phone_number, address, status, created_at, updated_at, created_by_id, updated_by_id)"+
    "VALUES (:first_name, :last_name, :phone_number, :address, :status, :created_at, :updated_at, :created_by_id, :updated_by_id) " +
            "RETURNING id", nativeQuery = true)
    Integer createCustomer(
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("phone_number") String phoneNumber,
            @Param("address") String address,
            @Param("status") Integer status,
            @Param("created_at") LocalDateTime createdAt,
            @Param("updated_at") LocalDateTime updatedAt,
            @Param("created_by_id") Long createdById,
            @Param("updated_by_id") Long updatedById
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE customers e SET first_name = :first_name, last_name = :last_name, phone_number = :phone_number, address = :address, updated_at = :updated_at WHERE id = :id", nativeQuery = true)
    int updateCustomerById(
            @Param("id") Long id,
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("phone_number") String phoneNumber,
            @Param("address") String address,
            @Param("updated_at") LocalDateTime updatedAt
    );

    @Transactional
    @Query(value = "SELECT * FROM customers where id = :id", nativeQuery = true)
    Customer getCustomerById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customers where id = :id", nativeQuery = true)
    int deleteCustomerById(@Param("id") Long id);


}
