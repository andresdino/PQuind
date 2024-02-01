package com.example.pruebatecnicaquind.Dto;


import com.example.pruebatecnicaquind.Entity.Auditoria;
import com.example.pruebatecnicaquind.Enums.EstadoCuenta;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CuentaCorrienteDto  extends Auditoria {

    private String tipoCuenta;
    private String numeroCuenta;
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;
    private BigDecimal saldo;
    private boolean exentaGMF;

}
