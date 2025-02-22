package com.example.InventoryManagementSystem.mapper;
import com.example.InventoryManagementSystem.dto.supplier.MasterSupplierDTO;
import com.example.InventoryManagementSystem.dto.supplier.SupplierResponseDTO;
import com.example.InventoryManagementSystem.model.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper extends BaseMapper<Supplier, MasterSupplierDTO> {

    @Override
    SupplierResponseDTO toDto(Supplier supplier);
    Supplier toEntity(MasterSupplierDTO masterSupplierDTO);
}
