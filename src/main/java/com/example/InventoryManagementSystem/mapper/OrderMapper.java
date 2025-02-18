package com.example.InventoryManagementSystem.mapper;

import com.example.InventoryManagementSystem.dto.order.MasterOrderDto;
import com.example.InventoryManagementSystem.dto.order.OrderResponseDto;
import com.example.InventoryManagementSystem.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper<Order, MasterOrderDto> {

    @Override
    @Mapping(source = "customerId", target = "customer.id") // Fix customer mapping
    Order toEntity(MasterOrderDto masterOrderDto);

    @Override
    @Mapping(source = "customer.id", target = "customerId")
    OrderResponseDto toDto(Order order);
}