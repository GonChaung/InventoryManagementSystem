package com.example.InventoryManagementSystem.repository;

import com.example.InventoryManagementSystem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {


    @Query(value = "SELECT * FROM order_items", nativeQuery = true)
    List<OrderItem> getAllOrderItems();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO order_items (order_id, item_id, item_discount, quantity, total_item_cost, created_at, updated_at) " +
            "VALUES (:orderId, :itemId, :itemDiscount, :quantity, :totalItemCost, :createdAt, :updatedAt) ",
            nativeQuery = true)
    void createOrderItem(
            @Param("orderId") Long orderId,
            @Param("itemId") Long itemId,
            @Param("itemDiscount") Double itemDiscount,
            @Param("quantity") int quantity,
            @Param("totalItemCost") Double totalItemCost,
            @Param("createdAt") LocalDateTime createdAt,
            @Param("updatedAt") LocalDateTime updatedAt
    );

    @Query(value = "SELECT * FROM order_items WHERE id = :id", nativeQuery = true)
    Optional<OrderItem> findById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE order_items SET item_discount = :#{#orderItemUpdate.itemDiscount}, " +
            "quantity = :#{#orderItemUpdate.quantity}, " +
            "total_item_cost = :#{#orderItemUpdate.totalItemCost}, " +
            "updated_at = :#{#orderItemUpdate.updatedAt} " +
            "WHERE id = :#{#orderItemUpdate.id}", nativeQuery = true)
    int updateOrderItemById(@Param("orderItemUpdate") OrderItem orderItemUpdate);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM order_items WHERE id = :id", nativeQuery = true)
    void deleteOrderItemById(@Param("id") Long id);
}
