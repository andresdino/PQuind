package com.example.pruebatecnicaquind.Service.Implementation;

import com.example.pruebatecnicaquind.Entity.Cliente;
import com.example.pruebatecnicaquind.Entity.Cuentas.CuentaAhorro;

import java.math.BigDecimal;

public interface ICuentaAhorroService {

    public CuentaAhorro crearCuentaAhorro(Cliente cliente);

    void consignar(Long cuentaAhorroId, BigDecimal monto);

    void retirar(Long cuentaAhorroId, BigDecimal monto);

    void transferir(Long cuentaAhorroId, Long destinoId, BigDecimal monto);

}
