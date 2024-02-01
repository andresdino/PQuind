package com.example.pruebatecnicaquind.Service;

import com.example.pruebatecnicaquind.Entity.Cliente;
import com.example.pruebatecnicaquind.Entity.Cuentas.CuentaCorriente;
import com.example.pruebatecnicaquind.Repository.CuentaCorrienteRepository;
import com.example.pruebatecnicaquind.Service.Implementation.ICuentaCorrienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class CuentaCorrienteService implements ICuentaCorrienteService {


    @Autowired
    private CuentaCorrienteRepository cuentaCorrienteRepository;

    @Override
    public CuentaCorriente crearCuentaCorriente(Cliente cliente) {
        CuentaCorriente cuentaCorriente = new CuentaCorriente();
        cuentaCorriente.setCliente(cliente);
        cuentaCorriente.setFechaCreacion(LocalDateTime.now());
        cuentaCorriente.setActiva(true);
        return cuentaCorrienteRepository.save(cuentaCorriente);
    }

    @Override
    @Transactional
    public void consignar(Long cuentaCorrienteId, BigDecimal monto) {
        CuentaCorriente cuentaCorriente = cuentaCorrienteRepository.findById(cuentaCorrienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente no encontrada con ID: " + cuentaCorrienteId));

        // Lógica de consignación
        cuentaCorriente.setSaldo(cuentaCorriente.getSaldo().add(monto));
        cuentaCorriente.setFechaModificacion(LocalDateTime.now());

        cuentaCorrienteRepository.save(cuentaCorriente);
    }

    @Override
    @Transactional
    public void retirar(Long cuentaCorrienteId, BigDecimal monto) {
        CuentaCorriente cuentaCorriente = cuentaCorrienteRepository.findById(cuentaCorrienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente no encontrada con ID: " + cuentaCorrienteId));

        // Lógica de retiro
        if (cuentaCorriente.getSaldo().compareTo(monto) >= 0) {
            cuentaCorriente.setSaldo(cuentaCorriente.getSaldo().subtract(monto));
            cuentaCorriente.setFechaModificacion(LocalDateTime.now());

            cuentaCorrienteRepository.save(cuentaCorriente);
        } else {
            throw new IllegalStateException("Saldo insuficiente para realizar el retiro.");
        }
    }

    @Override
    @Transactional
    public void transferir(Long cuentaCorrienteId, Long destinoId, BigDecimal monto) {
        CuentaCorriente cuentaCorriente = cuentaCorrienteRepository.findById(cuentaCorrienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente no encontrada con ID: " + cuentaCorrienteId));

        // Lógica de transferencia
        retirar(cuentaCorrienteId, monto);

        CuentaCorriente destino = cuentaCorrienteRepository.findById(destinoId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente de destino no encontrada con ID: " + destinoId));

        destino.consignar(monto);
    }
}
