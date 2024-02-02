package com.example.pruebatecnicaquind.Service.Impl;

import com.example.pruebatecnicaquind.Constans.MessageAplication;
import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Dto.RequestCuentaClienteDto;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import com.example.pruebatecnicaquind.Enum.EstadoCuenta;
import com.example.pruebatecnicaquind.Enum.TipoCuenta;
import com.example.pruebatecnicaquind.Repository.CuentaRepository;
import com.example.pruebatecnicaquind.Repository.ProductoRepository;
import com.example.pruebatecnicaquind.Service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private CuentaAhorroImpl cuentaAhorro;
    @Override
    public Object createCuenta(RequestCuentaClienteDto requestCuentaClienteDto) {
        requestCuentaClienteDto.getProductoDto().setNumeroCuenta(null);
        String numeroCuenta;
        if (requestCuentaClienteDto.getTipoCuenta().equals(TipoCuenta.AHORROS)){
            return  cuentaAhorro.crearCuentaAhorro(requestCuentaClienteDto);
        } else if (requestCuentaClienteDto.getTipoCuenta().equals(TipoCuenta.CORRIENTE)) {
            numeroCuenta = String.format("33%08d");
            requestCuentaClienteDto.getProductoDto().setNumeroCuenta(numeroCuenta);
            return null;
        } else{
            return MessageAplication.CANNOTCREATEDIFFERENTACCOUNT;
        }

    }

    @Override
    public Object updateEstadoCuenta(EditarEstadoCuentaDto editarEstadoCuentaDto) {
        Optional<ProductoEntity> productoEntity = productoRepository.findProductoEntityByNumeroCuenta(editarEstadoCuentaDto.getNumeroCuenta());
        if (productoEntity.isPresent()) {
            productoEntity.get().setEstado(editarEstadoCuentaDto.getEstado());
            productoEntity.get().setFechaModificacion(LocalDateTime.now());
            productoRepository.save(productoEntity.get());
            return MessageAplication.UPDATEACCOUNTSTATUSCORRECTLY;
        }
        return MessageAplication.ACCOUNTNOTFOUND;
    }

    @Override
    public Object cancelarCuenta(EditarEstadoCuentaDto editarEstadoCuentaDto) {
        Optional<ProductoEntity> productoEntity = productoRepository.findProductoEntityByNumeroCuenta(editarEstadoCuentaDto.getNumeroCuenta());
        if (productoEntity.isPresent()){
            if (productoEntity.get().getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                productoEntity.get().setEstado(EstadoCuenta.CANCELADA);
                productoEntity.get().setFechaModificacion(LocalDateTime.now());
                productoRepository.save(productoEntity.get());
                return MessageAplication.ACCOUNTCANCELLED;
            }
            return MessageAplication.ACCOUNTCANNOTCANCELLED;
        }
        return MessageAplication.ACCOUNTNOTFOUND;
    }

    @Override
    public Object consignarDinero(String numeroCuenta, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
           return MessageAplication.AMOUNTAPPROPRIATIONMUSTBEPOSITIVE;
        }

        Optional<ProductoEntity> productoEntity = productoRepository.findProductoEntityByNumeroCuenta(numeroCuenta);
        if (productoEntity.isEmpty()){
            return "Cuenta de ahorro no encontrada con ID:";
        }

        productoEntity.get().setSaldo(productoEntity.get().getSaldo().add(monto));
        productoEntity.get().setFechaModificacion(LocalDateTime.now());

        return productoRepository.save(productoEntity.get());
    }

    @Override
    public Object retirarDinero(String numeroCuenta, BigDecimal monto) {
        Optional<ProductoEntity> productoEntity = productoRepository.findProductoEntityByNumeroCuenta(numeroCuenta);

        if (productoEntity.isEmpty()){
            return "Cuenta de ahorro no encontrada con ID:";
        }
        // Lógica de retiro
        if (productoEntity.get().getSaldo().compareTo(monto) >= 0) {
            productoEntity.get().setSaldo(productoEntity.get().getSaldo().subtract(monto));
            productoEntity.get().setFechaModificacion(LocalDateTime.now());

            return productoRepository.save(productoEntity.get());
        } else {
            throw new IllegalStateException("Saldo insuficiente para realizar el retiro.");
        }
    }

    @Override
    public void tranferirDinero(String origenNumeroCuenta,  String destinoNumeroCuenta, BigDecimal monto) {
        retirarDinero(origenNumeroCuenta,monto);
        consignarDinero(destinoNumeroCuenta,monto);
    }

    public String generarNumeroUnico() {
        Random random = new Random();
        String numeroGenerado;

        do {
            // Generar un número aleatorio
            numeroGenerado = String.valueOf(random.nextInt(10000)); // Ajusta el rango según tus necesidades

            // Verificar si el número ya existe en la base de datos
        } while (productoRepository.findByNumeroCuenta(numeroGenerado));

        return numeroGenerado;
    }

    private static final AtomicLong contador = new AtomicLong(3);
    public String estadoCuenta() {
        long siguienteNumero = contador.getAndIncrement();

        // Formatear como "53" seguido de 8 dígitos
        String numeroCuenta = String.format("53%08d");
        return numeroCuenta;
    }


}
