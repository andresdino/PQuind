package com.example.pruebatecnicaquind.Service.Implementation;

import com.example.pruebatecnicaquind.Entity.Cliente;
import com.example.pruebatecnicaquind.Entity.Cuentas.CuentaCorriente;

import java.math.BigDecimal;

public interface ICuentaCorrienteService {

    public CuentaCorriente crearCuentaCorriente(Cliente cliente);

    void consignar(Long cuentaCorrienteId, BigDecimal monto);

    void retirar(Long cuentaCorrienteId, BigDecimal monto);

    void transferir(Long cuentaCorrienteId, Long destinoId, BigDecimal monto);

}
