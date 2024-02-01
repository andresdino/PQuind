package com.example.pruebatecnicaquind.Service.Implementation;

import com.example.pruebatecnicaquind.Dto.ProductoDTO;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;

import java.math.BigDecimal;

public interface IProductoService {

    ProductoEntity createProducto(ProductoDTO productoDTO);

    void cancelarCuenta(Long productoId);

    void consignar(Long productoId, BigDecimal monto);

    void retirar(Long productoId, BigDecimal monto);

    void transferir(Long origenProductoId, Long destinoProductoId, BigDecimal monto);

}
