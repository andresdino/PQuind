package com.example.pruebatecnicaquind.Service;

import com.example.pruebatecnicaquind.Constants.MessageAplication;
import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Entity.CuentaAhorroEntity;
import com.example.pruebatecnicaquind.Entity.CuentaCorrienteEntity;
import com.example.pruebatecnicaquind.Enums.EstadoCuenta;
import com.example.pruebatecnicaquind.Repository.CuentaCorrienteRepository;
import com.example.pruebatecnicaquind.Service.Implementation.ICuentaCorrienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CuentaCorrienteService implements ICuentaCorrienteService {


    @Autowired
    private CuentaCorrienteRepository cuentaCorrienteRepository;

    @Override
    public CuentaCorrienteEntity crearCuentaCorriente(ClienteEntity clienteEntity) {
        CuentaCorrienteEntity cuentaCorrienteEntity = new CuentaCorrienteEntity();
        cuentaCorrienteEntity.setClienteEntity(clienteEntity);
        cuentaCorrienteEntity.setFechaCreacion(LocalDateTime.now());
        cuentaCorrienteEntity.setActiva(true);
        return cuentaCorrienteRepository.save(cuentaCorrienteEntity);
    }

    @Override
    public Object updateEstadoCuenta(EditarEstadoCuentaDto editarEstadoCuentaDto) {
        Optional<CuentaCorrienteEntity> cuentaCorrienteEntity = cuentaCorrienteRepository.findCuentaCorrienteEntityByNumeroCuenta(editarEstadoCuentaDto.getNumeroCuenta());
        if (cuentaCorrienteEntity.isPresent()){
            cuentaCorrienteEntity.get().setEstado(editarEstadoCuentaDto.getEstado());
            cuentaCorrienteEntity.get().setFechaModificacion(LocalDateTime.now());
            cuentaCorrienteRepository.save(cuentaCorrienteEntity.get());
        }
        return MessageAplication.ACCOUNTNOTFOUND;
    }

    @Override
    public Object cancelarCuentaCorriente(EditarEstadoCuentaDto editarEstadoCuentaDto) {
        Optional<CuentaCorrienteEntity> cuentaCorrienteEntity = cuentaCorrienteRepository.findCuentaCorrienteEntityByNumeroCuenta(editarEstadoCuentaDto.getNumeroCuenta());
        if (cuentaCorrienteEntity.isPresent()){
            if (cuentaCorrienteEntity.get().getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                cuentaCorrienteEntity.get().setFechaModificacion(LocalDateTime.now());
                cuentaCorrienteEntity.get().setEstado(EstadoCuenta.CANCELADA);
                cuentaCorrienteRepository.save(cuentaCorrienteEntity.get());
            }
            return MessageAplication.ACCOUNTCANNOTCANCELLED;
        }
        return MessageAplication.ACCOUNTNOTFOUND;
    }

    @Override
    public void consignar(String cuentaCorrienteId, BigDecimal monto) {
        CuentaCorrienteEntity cuentaCorrienteEntity = cuentaCorrienteRepository.findCuentaCorrienteEntityByNumeroCuenta(cuentaCorrienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente no encontrada con ID: " + cuentaCorrienteId));

        cuentaCorrienteEntity.setSaldo(cuentaCorrienteEntity.getSaldo().add(monto));
        cuentaCorrienteEntity.setFechaModificacion(LocalDateTime.now());

        cuentaCorrienteRepository.save(cuentaCorrienteEntity);
    }

    @Override
    public void retirar(String cuentaCorrienteId, BigDecimal monto) {
        CuentaCorrienteEntity cuentaCorrienteEntity = cuentaCorrienteRepository.findCuentaCorrienteEntityByNumeroCuenta(cuentaCorrienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente no encontrada con ID: " + cuentaCorrienteId));

        // Lógica de retiro
        if (cuentaCorrienteEntity.getSaldo().compareTo(monto) >= 0) {
            cuentaCorrienteEntity.setSaldo(cuentaCorrienteEntity.getSaldo().subtract(monto));
            cuentaCorrienteEntity.setFechaModificacion(LocalDateTime.now());

            cuentaCorrienteRepository.save(cuentaCorrienteEntity);
        } else {
            throw new IllegalStateException("Saldo insuficiente para realizar el retiro.");
        }
    }


    @Override
    @Transactional
    public void transferir(String cuentaCorrienteId, Long destinoId, BigDecimal monto) {
        CuentaCorrienteEntity cuentaCorrienteEntity = cuentaCorrienteRepository.findCuentaCorrienteEntityByNumeroCuenta(cuentaCorrienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente no encontrada con ID: " + cuentaCorrienteId));

        // Lógica de transferencia
        retirar(cuentaCorrienteId, monto);

        CuentaCorrienteEntity destino = cuentaCorrienteRepository.findById(destinoId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente de destino no encontrada con ID: " + destinoId));

        destino.consignar(monto);
    }
}
