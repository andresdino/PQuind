package com.example.pruebatecnicaquind.Dto;


import com.example.pruebatecnicaquind.Entity.Auditoria;
import com.example.pruebatecnicaquind.Enum.EstadoCuenta;
import com.example.pruebatecnicaquind.Enum.TipoCuenta;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class RequestCuentaClienteDto {

    private String tipoCuenta;

    private ProductoDto productoDto;

}
