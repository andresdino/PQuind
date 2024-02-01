package com.example.pruebatecnicaquind.Entity.Cuentas;

import com.example.pruebatecnicaquind.Entity.Producto;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "cuenta_ahorro_id")
public class CuentaAhorro extends Producto {

    public CuentaAhorro() {
        this.setActiva(true); // Establecer como activa de forma predeterminada al crear la cuenta de ahorro
    }

    @Override
    public void setSaldo(BigDecimal saldo) {
        if (saldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo de una cuenta de ahorros no puede ser menor a $0.");
        }

        super.setSaldo(saldo);
    }

    @Override
    public void setNumeroCuenta(String numeroCuenta) {
        if (numeroCuenta.startsWith("53") && numeroCuenta.length() == 10) {
            super.setNumeroCuenta(numeroCuenta);
        } else {
            throw new IllegalArgumentException("El número de cuenta de ahorro debe empezar con '53' y tener una longitud de 10 dígitos.");
        }
    }

    @Override
    public void realizarTransaccion(BigDecimal monto) {
        consignar(monto);
    }


}