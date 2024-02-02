package com.example.pruebatecnicaquind.Mapper;

import com.example.pruebatecnicaquind.Dto.ProductoDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;

public class ProductoMapper {

    public static ProductoEntity dtoToProductoEntity(ProductoDto productoDto) {
        return ProductoEntity
                .builder()
                .numeroCuenta(productoDto.getNumeroCuenta())
                .estado(productoDto.getEstado())
                .clienteEntity(ClienteEntity
                        .builder()
                        .id(productoDto.getClienteId())
                        .build())
                .build();
    }
}
