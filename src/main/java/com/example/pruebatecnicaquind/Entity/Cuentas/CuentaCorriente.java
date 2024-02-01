package com.example.pruebatecnicaquind.Entity.Cuentas;

import com.example.pruebatecnicaquind.Entity.Producto;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "cuenta_corriente_id")
public class CuentaCorriente extends Producto {

    @Override
    public void setNumeroCuenta(String numeroCuenta) {
        if (numeroCuenta.startsWith("33") && numeroCuenta.length() == 10) {
            super.setNumeroCuenta(numeroCuenta);
        } else {
            throw new IllegalArgumentException("El número de cuenta corriente debe empezar con '33' y tener una longitud de 10 dígitos.");
        }
    }

    @Override
    public void realizarTransaccion(BigDecimal monto) {
        consignar(monto);
    }



}
