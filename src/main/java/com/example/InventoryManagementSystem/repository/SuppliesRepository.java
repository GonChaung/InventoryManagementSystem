package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Supplies;
import com.example.InventoryManagementSystem.model.constant.PaymentStatus;
import com.example.InventoryManagementSystem.model.constant.ReceivedStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface SuppliesRepository extends CrudRepository<Supplies, Long> {

    @Query(value = "SELECT * FROM supplies", nativeQuery = true)
    List<Supplies> getAllSupplies();


    @Transactional
    @Query(value = "INSERT INTO supplies(quantity, unit_price, discount, total_cost, payment_status, order_date, received_date, received_status, supplier_id, item_id)"+
    "VALUES (:quantity, :unit_price, :discount, :total_cost, :payment_status, :order_date, :received_date, :received_status, :supplier_id, :item_id)" +
    "RETURNING id", nativeQuery = true)
    int createSupplies(
            @Param("quantity") Integer quantity,
            @Param("unit_price") Double unitPrice,
            @Param("discount") Double discount,
            @Param("total_cost") Double totalCost,
            @Param("payment_status") Integer paymentStatus,
            @Param("order_date")Date orderDate, //change date?
            @Param("received_date") Date receivedDate,
            @Param("received_status") Integer receivedStatus,
            @Param("supplier_id") Long supplierId,
            @Param("item_id") Long itemId,
            @Param("status") Integer status,
            @Param("created_at") LocalDateTime createdAt,
            @Param("updatedAt") LocalDateTime updatedAt,
            @Param("createdById") Long createdById,
            @Param("updatedById") Long updatedById
    );


    @Modifying
    @Transactional
    @Query(value = "UPDATE supplies SET quantity = :quantity, unit_price = :unitPrice, discount = :discount, total_cost = :totalCost, payment_status = :paymentStatus, " +
                   "order_date = :orderDate, received_date = :receivedDate, received_status = :receivedStatus, supplier_id = :supplierId, item_id = :itemId, updated_at = :updated_at WHERE id = :id", nativeQuery = true)
    int updateSuppliesById(
            @Param("id") Long id,
            @Param("quantity") Integer quantity,
            @Param("unit_price") Double unitPrice,
            @Param("discount") Double discount,
            @Param("total_cost") Double totalCost,
            @Param("payment_status") Integer paymentStatus,
            @Param("order_date")Date orderDate,
            @Param("received_date") Date receivedDate,
            @Param("received_status") Integer receivedStatus,
            @Param("supplier_id") Long supplierId,
            @Param("item_id") Long itemId,
            @Param("updated_at") LocalDateTime updatedAt
    );

    @Transactional
    @Query(value = "SELECT * FROM supplies where id = :id", nativeQuery = true)
    Supplies getSuppliesById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM supplies where id = :id", nativeQuery = true)
    int deleteSuppliesById(@Param("id") Long id);


}







