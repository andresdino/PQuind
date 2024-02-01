package com.example.pruebatecnicaquind.Service.Implementation;

import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Entity.CuentaCorrienteEntity;

import java.math.BigDecimal;

public interface ICuentaCorrienteService {

    public CuentaCorrienteEntity crearCuentaCorriente(ClienteEntity clienteEntity);
    Object updateEstadoCuenta(EditarEstadoCuentaDto editarEstadoCuentaDto);

    Object cancelarCuentaCorriente(EditarEstadoCuentaDto editarEstadoCuentaDto);

    void consignar(String cuentaCorrienteId, BigDecimal monto);

    void retirar(String cuentaCorrienteId, BigDecimal monto);

    void transferir(String cuentaCorrienteId, Long destinoId, BigDecimal monto);

}
