package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.dto.order.MasterOrderDto;
import com.example.InventoryManagementSystem.model.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders", nativeQuery = true)
    List<Order> getAllOrders();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO orders (order_date, order_discount, total_cost, order_status, customer_id, status, created_at, updated_at, created_by_id, updated_by_id) " +
            "VALUES (:orderDate, :orderDiscount, :totalCost, :orderStatus, :customerId, :status, :createdAt, :updatedAt, :createdById, :updatedById) ",
            nativeQuery = true)
    void createOrder(
            @Param("orderDate") LocalDateTime orderDate,
            @Param("orderDiscount") Double orderDiscount,
            @Param("totalCost") Double totalCost,
            @Param("orderStatus") Integer orderStatus,
            @Param("customerId") Long customerId,
            @Param("status") Integer status,
            @Param("createdAt") LocalDateTime createdAt,
            @Param("updatedAt") LocalDateTime updatedAt,
            @Param("createdById") Long createdById,
            @Param("updatedById") Long updatedById
    );

    @Query(value = "SELECT * FROM orders WHERE id = :id", nativeQuery = true)
    Optional<Order> findById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET order_discount = :#{#orderUpdate.orderDiscount}, " +
            "total_cost = :#{#orderUpdate.totalCost}, " +
            "order_status = :#{#orderUpdate.orderStatus}, " +
            "customer_id = :#{#orderUpdate.customer.id}, " +
            "updated_at = :#{#orderUpdate.updatedAt} " +
            "WHERE id = :#{#orderUpdate.id}", nativeQuery = true)
    int updateOrderById(@Param("orderUpdate") Order orderUpdate);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM orders WHERE id = :id", nativeQuery = true)
    void deleteOrderById(@Param("id") Long id);
}
