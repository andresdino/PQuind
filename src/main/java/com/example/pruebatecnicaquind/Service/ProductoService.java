package com.example.pruebatecnicaquind.Service;

import com.example.pruebatecnicaquind.Entity.Producto;
import com.example.pruebatecnicaquind.Repository.ProductoRepository;
import com.example.pruebatecnicaquind.Service.Implementation.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ProductoService implements IProductoService {


    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void realizarTransaccion(Long productoId, BigDecimal monto) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productoId));
        producto.realizarTransaccion(monto);
        productoRepository.save(producto);
    }

    @Transactional
    public void consignar(Long productoId, BigDecimal monto) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productoId));

        producto.consignar(monto);
        productoRepository.save(producto);
    }

    @Transactional
    public void retirar(Long productoId, BigDecimal monto) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productoId));

        producto.retirar(monto);
        productoRepository.save(producto);
    }

    @Transactional
    public void transferir(Long origenProductoId, Long destinoProductoId, BigDecimal monto) {
        Producto origen = productoRepository.findById(origenProductoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto de origen no encontrado con ID: " + origenProductoId));

        Producto destino = productoRepository.findById(destinoProductoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto de destino no encontrado con ID: " + destinoProductoId));

        origen.transferir(destino, monto);
        productoRepository.save(origen);
        productoRepository.save(destino);
    }

    @Transactional
    public void cancelarCuenta(Long productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productoId));

        producto.cancelarCuenta();
        productoRepository.save(producto);
    }

}
