package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.orderItem.MasterOrderItemDto;
import com.example.InventoryManagementSystem.dto.orderItem.OrderItemResponseDto;
import com.example.InventoryManagementSystem.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends BaseMapper<OrderItem, MasterOrderItemDto> {
    @Override
    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "itemId", target = "item.id")
    OrderItem toEntity(MasterOrderItemDto masterOrderItem);

    @Override
    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "item.id", target = "itemId")
    OrderItemResponseDto toDto(OrderItem orderItem);
}
