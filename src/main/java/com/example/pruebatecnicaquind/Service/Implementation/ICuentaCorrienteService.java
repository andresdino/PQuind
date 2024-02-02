package com.example.pruebatecnicaquind.Service.Implementation;

import com.example.pruebatecnicaquind.Dto.CuentaCorrienteDto;
import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Entity.CuentaCorrienteEntity;

import java.math.BigDecimal;

public interface ICuentaCorrienteService {

    CuentaCorrienteEntity crearCuentaCorriente(ClienteEntity clienteEntity, CuentaCorrienteDto cuentaCorrienteDto);

    Object updateEstadoCuenta(EditarEstadoCuentaDto editarEstadoCuentaDto);

    Object cancelarCuentaCorriente(EditarEstadoCuentaDto editarEstadoCuentaDto);

    void consignar(String cuentaCorrienteId, BigDecimal monto);

    void retirar(String cuentaCorrienteId, BigDecimal monto);

    void transferir(String cuentaCorrientenumeroCuenta, String destinoIdnumeroCuenta, BigDecimal monto);

}
