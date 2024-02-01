package com.example.pruebatecnicaquind.Service.Implementation;

import com.example.pruebatecnicaquind.Entity.Producto;

import java.math.BigDecimal;

public interface IProductoService {

    Producto createProducto(Producto producto);

    void cancelarCuenta(Long productoId);

    void realizarTransaccion(Long productoId, BigDecimal monto);

    void consignar(Long productoId, BigDecimal monto);

    void retirar(Long productoId, BigDecimal monto);

    void transferir(Long origenProductoId, Long destinoProductoId, BigDecimal monto);

}
