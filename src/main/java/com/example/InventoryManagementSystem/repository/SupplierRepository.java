package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Supplier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    @Query(value = "SELECT * FROM suppliers", nativeQuery = true)
    List<Supplier> getAllSuppliers();

    @Transactional
    @Query(value = "INSERT INTO suppliers (first_name, last_name, phone_number, email, address, status, created_at, updated_at, created_by_id, updated_by_id)"+
    "VALUES (:first_name, :last_name, :phone_number, :email, :address, :status, :created_at, :updated_at, :created_by_id, :updated_by_id) " +
            "RETURNING id", nativeQuery = true)
    Integer createSupplier(
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("phone_number") String phoneNumber,
            @Param("email") String email,
            @Param("address") String address,
            @Param("status") Integer status,
            @Param("created_at") LocalDateTime createdAt,
            @Param("updated_at") LocalDateTime updatedAt,
            @Param("created_by_id") Long createdById,
            @Param("updated_by_id") Long updatedById
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE suppliers e SET first_name = :first_name, last_name = :last_name, phone_number = :phone_number, email = :email, address = :address, updated_at = :updated_at WHERE id = :id", nativeQuery = true)
    int updateSupplierById(
            @Param("id") Long id,
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("phone_number") String phoneNumber,
            @Param("email") String email,
            @Param("address") String address,
            @Param("updated_at") LocalDateTime updatedAt
    );

    @Transactional
    @Query(value = "SELECT * FROM suppliers where id = :id", nativeQuery = true)
    Supplier getSupplierById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM suppliers where id = :id", nativeQuery = true)
    int deleteSupplierById(@Param("id") Long id);


}
