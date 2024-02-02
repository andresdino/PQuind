package com.example.pruebatecnicaquind.Dto;


import com.example.pruebatecnicaquind.Enum.TipoCuenta;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RequestCuentaClienteDto {

    private TipoCuenta tipoCuenta;

    private ProductoDto productoDto;
}
