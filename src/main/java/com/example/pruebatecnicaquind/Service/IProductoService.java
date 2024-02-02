package com.example.pruebatecnicaquind.Service;

import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Dto.RequestCuentaClienteDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface IProductoService {

    Object createCuenta(RequestCuentaClienteDto requestCuentaClienteDto);
    Object updateEstadoCuenta(EditarEstadoCuentaDto requestCuentaClienteDto);
    Object cancelarCuenta(EditarEstadoCuentaDto requestCuentaClienteDto);
    Object consignarDinero(String numeroCuenta, BigDecimal monto);
    Object retirarDinero(String numeroCuenta, BigDecimal monto);
    void tranferirDinero(String origenCuenta, String destinoCuenta, BigDecimal monto);
}
