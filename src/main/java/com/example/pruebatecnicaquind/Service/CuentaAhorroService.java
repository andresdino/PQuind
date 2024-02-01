package com.example.pruebatecnicaquind.Service;

import com.example.pruebatecnicaquind.Entity.Cliente;
import com.example.pruebatecnicaquind.Entity.Cuentas.CuentaAhorro;
import com.example.pruebatecnicaquind.Repository.CuentaAhorroRepository;
import com.example.pruebatecnicaquind.Service.Implementation.ICuentaAhorroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CuentaAhorroService implements ICuentaAhorroService {

    @Autowired
    private CuentaAhorroRepository cuentaAhorroRepository;

    @Override
    @Transactional
    public CuentaAhorro crearCuentaAhorro(Cliente cliente) {
        CuentaAhorro cuentaAhorro = new CuentaAhorro();
        cuentaAhorro.setCliente(cliente);
        cuentaAhorro.setFechaCreacion(LocalDateTime.now());
        cuentaAhorro.setActiva(true);
        return cuentaAhorroRepository.save(cuentaAhorro);
    }

    @Override
    @Transactional
    public void consignar(Long cuentaAhorroId, BigDecimal monto) {
        CuentaAhorro cuentaAhorro = cuentaAhorroRepository.findById(cuentaAhorroId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente no encontrada con ID: " + cuentaAhorroId));

        // Lógica de consignación
        cuentaAhorro.setSaldo(cuentaAhorro.getSaldo().add(monto));
        cuentaAhorro.setFechaModificacion(LocalDateTime.now());

        cuentaAhorroRepository.save(cuentaAhorro);
    }

    @Override
    @Transactional
    public void retirar(Long cuentaAhorroId, BigDecimal monto) {
        CuentaAhorro cuentaAhorro = cuentaAhorroRepository.findById(cuentaAhorroId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente no encontrada con ID: " + cuentaAhorroId));

        // Lógica de retiro
        if (cuentaAhorro.getSaldo().compareTo(monto) >= 0) {
            cuentaAhorro.setSaldo(cuentaAhorro.getSaldo().subtract(monto));
            cuentaAhorro.setFechaModificacion(LocalDateTime.now());

            cuentaAhorroRepository.save(cuentaAhorro);
        } else {
            throw new IllegalStateException("Saldo insuficiente para realizar el retiro.");
        }
    }

    @Override
    @Transactional
    public void transferir(Long cuentaAhorroId, Long destinoId, BigDecimal monto) {
        CuentaAhorro cuentaAhorro = cuentaAhorroRepository.findById(cuentaAhorroId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente no encontrada con ID: " + cuentaAhorroId));

        // Lógica de transferencia
        retirar(cuentaAhorroId, monto);

        CuentaAhorro destino = cuentaAhorroRepository.findById(destinoId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente de destino no encontrada con ID: " + destinoId));

        destino.consignar(monto);
    }
}
