package com.example.pruebatecnicaquind.Mapper;

import com.example.pruebatecnicaquind.Dto.ProductoDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;

public class ProductoMapper {

    public static ProductoEntity dtoToProductoEntity(ProductoDto productoDto) {
        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setEstado(productoDto.getEstado());
        productoEntity.setSaldo(productoDto.getSaldo());
        productoEntity.setNumeroCuenta(productoDto.getNumeroCuenta());
        productoEntity.setClienteEntity(ClienteEntity
                .builder()
                .id(productoDto.getClienteId())
                .build());
        productoEntity.setFechaCreacion(productoDto.getFechaCreacion());
        productoEntity.setExentaGMF(productoDto.isExentaGMF());

        return productoEntity;

    }
}
