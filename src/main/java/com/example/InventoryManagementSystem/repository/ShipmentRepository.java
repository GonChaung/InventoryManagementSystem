package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.Shipment;
import com.example.InventoryManagementSystem.model.constant.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Query(value = "SELECT * FROM shipments", nativeQuery = true)
    List<Shipment> getAllShipments();

    @Transactional
    @Query(value = "INSERT INTO shipments (shipment_date, shipment_status, delivery_company, order_id, status, created_at, updated_at, created_by_id, updated_by_id) " +
            "VALUES (:shipment_date, :shipment_status, :delivery_company, :order_id, :status, :created_at, :updated_at, :created_by_id, :updated_by_id) " +
            "RETURNING id", nativeQuery = true)
    int createShipment(
            @Param("shipment_date") LocalDateTime shipmentDate,
            @Param("shipment_status") Integer shipmentStatus,
            @Param("delivery_company") String deliveryCompany,
            @Param("order_id") Long orderId,
            @Param("status") Integer status,
            @Param("created_at") LocalDateTime createdAt,
            @Param("updated_at") LocalDateTime updatedAt,
            @Param("created_by_id") Long createdById,
            @Param("updated_by_id") Long updatedById
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE shipments SET shipment_date = :shipment_date, shipment_status = :shipment_status, delivery_company = :delivery_company, updated_at = :updated_at WHERE id = :id", nativeQuery = true)
    int updateShipmentById(
            @Param("id") Long id,
            @Param("shipment_date") LocalDateTime shipmentDate,
            @Param("shipment_status") Integer shipmentStatus,
            @Param("delivery_company") String deliveryCompany,
            @Param("updated_at") LocalDateTime updatedAt
    );

    @Query(value = "SELECT * FROM shipments WHERE id = :id", nativeQuery = true)
    Shipment getShipmentById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM shipments WHERE id = :id", nativeQuery = true)
    int deleteShipmentById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Shipment s WHERE s.order.id = :orderId")
    void deleteByOrderId(@Param("orderId") Long orderId);
}

