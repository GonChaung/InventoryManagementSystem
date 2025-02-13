package com.example.InventoryManagementSystem.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BaseMapper<T, D> {

    // Convert from entity to DTO
    D toDto(T entity);

    // Convert from DTO to entity
    T toEntity(D dto);

    // Convert a list of entities to a list of DTOs
    List<D> toDtoList(List<T> entityList);

    // Convert a list of DTOs to a list of entities
    List<T> toEntityList(List<D> dtoList);

    // Update an existing entity from a DTO
    void updateEntityFromDto(D dto, @MappingTarget T entity);
}