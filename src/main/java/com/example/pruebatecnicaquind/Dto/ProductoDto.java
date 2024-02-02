package com.example.pruebatecnicaquind.Dto;

import com.example.pruebatecnicaquind.Enum.EstadoCuenta;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductoDto {

    private String tipoCuenta;
    private String numeroCuenta;
    private EstadoCuenta estado;
    private BigDecimal saldo;
    private boolean exentaGMF;
    private Long clienteId;
}
