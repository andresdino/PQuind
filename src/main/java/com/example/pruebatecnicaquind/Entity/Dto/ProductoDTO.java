package com.example.pruebatecnicaquind.Entity.Dto;

import com.example.pruebatecnicaquind.Entity.Cuentas.EstadoCuenta;
import com.example.pruebatecnicaquind.Entity.Producto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductoDTO implements Serializable {
    private Long id;
    private String tipoCuenta;
    private String numeroCuenta;
    private EstadoCuenta estado;
    private BigDecimal saldo;
    private boolean exentaGMF;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private Long clienteId;
    private boolean activa;

    public ProductoDTO(Long id, String tipoCuenta, String numeroCuenta, EstadoCuenta estado,
                       BigDecimal saldo, boolean exentaGMF, LocalDateTime fechaCreacion,
                       LocalDateTime fechaModificacion, Long clienteId, boolean activa) {
        this.id = id;
        this.tipoCuenta = tipoCuenta;
        this.numeroCuenta = numeroCuenta;
        this.estado = estado;
        this.saldo = saldo;
        this.exentaGMF = exentaGMF;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.clienteId = clienteId;
        this.activa = activa;
    }

    public static ProductoDTO fromProducto(Producto producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getTipoCuenta(),
                producto.getNumeroCuenta(),
                producto.getEstado(),
                producto.getSaldo(),
                producto.isExentaGMF(),
                producto.getFechaCreacion(),
                producto.getFechaModificacion(),
                producto.getCliente() != null ? producto.getCliente().getId() : null,
                producto.isActiva()
        );
    }



}