package com.example.pruebatecnicaquind.Service;

import com.example.pruebatecnicaquind.Dto.ProductoDTO;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import com.example.pruebatecnicaquind.Enums.EstadoCuenta;
import com.example.pruebatecnicaquind.Repository.ProductoRepository;
import com.example.pruebatecnicaquind.Service.Implementation.IProductoService;
import com.example.pruebatecnicaquind.mapper.ProductoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ProductoService implements IProductoService {


    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public ProductoEntity createProducto(ProductoDTO productoDTO) {
        productoDTO.setFechaModificacion(LocalDateTime.now());
        productoDTO.setEstado(EstadoCuenta.ACTIVA);
        ProductoEntity saveInformation = ProductoMapper.dtoToProductoEntity(productoDTO);
        return productoRepository.save(saveInformation);
    }

    @Override
    public void cancelarCuenta(Long productoId) {

    }

    @Transactional
    public void consignar(Long productoId, BigDecimal monto) {
        ProductoEntity productoEntity = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productoId));

        productoEntity.setSaldo(productoEntity.getSaldo().add(monto));
        productoEntity.setFechaModificacion(LocalDateTime.now());
        productoRepository.save(productoEntity);
    }

    @Transactional
    public void retirar(Long productoId, BigDecimal monto) {
        ProductoEntity productoEntity = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productoId));

        if (monto.compareTo(BigDecimal.ZERO) > 0 && productoEntity.getSaldo().compareTo(monto) >= 0) {
            productoEntity.setSaldo(productoEntity.getSaldo().subtract(monto));
            productoEntity.setFechaModificacion(LocalDateTime.now());
        } else {
            throw new IllegalStateException("Monto inválido para retiro.");
        }

        productoRepository.save(productoEntity);
    }

    @Transactional
    public void transferir(Long origenProductoId, Long destinoProductoId, BigDecimal monto) {
        ProductoEntity origen = productoRepository.findById(origenProductoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto de origen no encontrado con ID: " + origenProductoId));


        ProductoEntity destino = productoRepository.findById(destinoProductoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto de destino no encontrado con ID: " + destinoProductoId));

        if (monto.compareTo(BigDecimal.ZERO) > 0 && origen.getSaldo().compareTo(monto) >= 0) {
            origen.setSaldo(origen.getSaldo().subtract(monto));
            origen.setFechaModificacion(LocalDateTime.now());
        } else {
            throw new IllegalStateException("Monto inválido para retiro.");
        }

        destino.setSaldo(destino.getSaldo().add(monto));
        destino.setFechaModificacion(LocalDateTime.now());

        productoRepository.save(origen);
        productoRepository.save(destino);
    }




}
