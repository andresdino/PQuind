package com.example.pruebatecnicaquind.Service;

import com.example.pruebatecnicaquind.Constants.MessageAplication;
import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Dto.CuentaAhorroDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Entity.CuentaAhorroEntity;
import com.example.pruebatecnicaquind.Enums.EstadoCuenta;
import com.example.pruebatecnicaquind.Repository.CuentaAhorroRepository;
import com.example.pruebatecnicaquind.Service.Implementation.ICuentaAhorroService;
import com.example.pruebatecnicaquind.mapper.CuentaAhorroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CuentaAhorroService implements ICuentaAhorroService {

    @Autowired
    private CuentaAhorroRepository cuentaAhorroRepository;

    @Override
    @Transactional
    public Object crearCuentaAhorro(ClienteEntity clienteEntity, CuentaAhorroDto cuentaAhorroDto) {

        if (cuentaAhorroDto.getSaldo().compareTo(BigDecimal.ZERO) < 0){
            return MessageAplication.BALANCECANNOTLESS0;
        }

        String numeroCuenta = generarNumeroCuenta();

        cuentaAhorroDto.setEstado(EstadoCuenta.ACTIVA);
        cuentaAhorroDto.setFechaCreacion(LocalDateTime.now());
        cuentaAhorroDto.setNumeroCuenta(numeroCuenta);

        CuentaAhorroEntity cuentaAhorroEntity = CuentaAhorroMapper.dtoToCuentaAhorroEntity(cuentaAhorroDto);
        cuentaAhorroEntity.setClienteEntity(clienteEntity);

        CuentaAhorroEntity saveInformation = CuentaAhorroMapper.dtoToCuentaAhorroEntity(cuentaAhorroDto);

        return cuentaAhorroRepository.save(cuentaAhorroEntity);

    }

    @Override
    public Object updateEstadoCuenta(EditarEstadoCuentaDto editarEstadoCuentaDto) {
        Optional<CuentaAhorroEntity> cuentaAhorroEntity = cuentaAhorroRepository.findCuentaAhorroEntityByNumeroCuenta(editarEstadoCuentaDto.getNumeroCuenta());
        if (cuentaAhorroEntity.isPresent()){
            cuentaAhorroEntity.get().setEstado(editarEstadoCuentaDto.getEstado());
            cuentaAhorroEntity.get().setFechaModificacion(LocalDateTime.now());
            cuentaAhorroRepository.save(cuentaAhorroEntity.get());
        }
        return MessageAplication.ACCOUNTNOTFOUND;

    }

    @Override
    public Object cancelarCuentaAhorro(EditarEstadoCuentaDto editarEstadoCuentaDto) {
        Optional<CuentaAhorroEntity> cuentaAhorroEntity = cuentaAhorroRepository.findCuentaAhorroEntityByNumeroCuenta(editarEstadoCuentaDto.getNumeroCuenta());
        if (cuentaAhorroEntity.isPresent()){
            if (cuentaAhorroEntity.get().getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                cuentaAhorroEntity.get().setEstado(EstadoCuenta.CANCELADA);
                cuentaAhorroEntity.get().setFechaModificacion(LocalDateTime.now());
                cuentaAhorroRepository.save(cuentaAhorroEntity.get());
            }
            return MessageAplication.ACCOUNTCANNOTCANCELLED;
        }
        return MessageAplication.ACCOUNTNOTFOUND;
    }

    @Override
    @Transactional
    public void consignar(Long cuentaAhorroId, BigDecimal monto) {

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto de consignación debe ser positivo.");
        }

        CuentaAhorroEntity cuentaAhorroEntity = cuentaAhorroRepository.findById(cuentaAhorroId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de ahorro no encontrada con ID: " + cuentaAhorroId));

        // Lógica de consignación
        cuentaAhorroEntity.setSaldo(cuentaAhorroEntity.getSaldo().add(monto));
        cuentaAhorroEntity.setFechaModificacion(LocalDateTime.now());

        cuentaAhorroRepository.save(cuentaAhorroEntity);
    }

    @Override
    @Transactional
    public void retirar(Long cuentaAhorroId, BigDecimal monto) {
        CuentaAhorroEntity cuentaAhorroEntity = cuentaAhorroRepository.findById(cuentaAhorroId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente no encontrada con ID: " + cuentaAhorroId));

        // Lógica de retiro
        if (cuentaAhorroEntity.getSaldo().compareTo(monto) >= 0) {
            cuentaAhorroEntity.setSaldo(cuentaAhorroEntity.getSaldo().subtract(monto));
            cuentaAhorroEntity.setFechaModificacion(LocalDateTime.now());

            cuentaAhorroRepository.save(cuentaAhorroEntity);
        } else {
            throw new IllegalStateException("Saldo insuficiente para realizar el retiro.");
        }
    }

    @Override
    @Transactional
    public void transferir(Long cuentaAhorroId, Long destinoId, BigDecimal monto) {
        CuentaAhorroEntity cuentaAhorroEntity = cuentaAhorroRepository.findById(cuentaAhorroId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente no encontrada con ID: " + cuentaAhorroId));

        // Lógica de transferencia
        retirar(cuentaAhorroId, monto);

        CuentaAhorroEntity destino = cuentaAhorroRepository.findById(destinoId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta corriente de destino no encontrada con ID: " + destinoId));

        this.consignar(monto);
    }


    private String generarNumeroCuenta() {
        UUID uuid = UUID.randomUUID();
        return "53" + uuid.toString().substring(24);
    }
}
