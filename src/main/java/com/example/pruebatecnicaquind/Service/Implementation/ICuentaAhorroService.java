package com.example.pruebatecnicaquind.Service.Implementation;

import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Dto.CuentaAhorroDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;

import java.math.BigDecimal;

public interface ICuentaAhorroService {

    Object crearCuentaAhorro(ClienteEntity clienteEntity, CuentaAhorroDto cuentaAhorroDto);
    Object updateEstadoCuenta(EditarEstadoCuentaDto editarEstadoCuentaDto);
    Object cancelarCuentaAhorro(EditarEstadoCuentaDto editarEstadoCuentaDto);

    void consignar(Long cuentaAhorroId, BigDecimal monto);

    void retirar(Long cuentaAhorroId, BigDecimal monto);

    void transferir(Long cuentaAhorroId, Long destinoId, BigDecimal monto);

}
