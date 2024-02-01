package com.example.pruebatecnicaquind.mapper;

import com.example.pruebatecnicaquind.Dto.ProductoDTO;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;

public class ProductoMapper {

    public static ProductoEntity dtoToProductoEntity(ProductoDTO productoDTO) {
        return ProductoEntity
                .builder()
                .estado(productoDTO.getEstado())
                .build();
    }

    public static ProductoDTO entityToProductoDto(ProductoEntity productoEntity) {
        return ProductoDTO.builder().build();
    }
}
