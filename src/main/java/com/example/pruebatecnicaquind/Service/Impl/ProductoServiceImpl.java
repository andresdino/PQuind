package com.example.pruebatecnicaquind.Service.Impl;

import com.example.pruebatecnicaquind.Constans.MessageAplication;
import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Dto.RequestCuentaClienteDto;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import com.example.pruebatecnicaquind.Enum.EstadoCuenta;
import com.example.pruebatecnicaquind.Enum.TipoCuenta;
import com.example.pruebatecnicaquind.Repository.ProductoRepository;
import com.example.pruebatecnicaquind.Service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CuentaImpl crearCuenta;
    @Override
    public Object createCuenta(RequestCuentaClienteDto requestCuentaClienteDto) {
        requestCuentaClienteDto.getProductoDto().setNumeroCuenta(null);
        String numeroCuenta;
        if (requestCuentaClienteDto.getTipoCuenta().equals(TipoCuenta.AHORROS.name())){
            numeroCuenta = generarNumeroCuentaAleatorio("53");
            requestCuentaClienteDto.getProductoDto().setNumeroCuenta(numeroCuenta);
            return  crearCuenta.crearCuentaAhorro(requestCuentaClienteDto);
        } else if (requestCuentaClienteDto.getTipoCuenta().equals(TipoCuenta.CORRIENTE.name())) {
            numeroCuenta = generarNumeroCuentaAleatorio("33");
            requestCuentaClienteDto.getProductoDto().setNumeroCuenta(numeroCuenta);
            return crearCuenta.crearCuentaCorriente(requestCuentaClienteDto);
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
            return MessageAplication.ACCOUNTNOTFOUND;
        }

        productoEntity.get().setSaldo(productoEntity.get().getSaldo().add(monto));
        productoEntity.get().setFechaModificacion(LocalDateTime.now());

        return productoRepository.save(productoEntity.get());
    }

    @Override
    public Object retirarDinero(String numeroCuenta, BigDecimal monto) {
        Optional<ProductoEntity> productoEntity = productoRepository.findProductoEntityByNumeroCuenta(numeroCuenta);

        if (productoEntity.isEmpty()){
            return MessageAplication.ACCOUNTNOTFOUND;
        }
        // Lógica de retiro
        if (productoEntity.get().getSaldo().compareTo(monto) >= 0) {
            productoEntity.get().setSaldo(productoEntity.get().getSaldo().subtract(monto));
            productoEntity.get().setFechaModificacion(LocalDateTime.now());

            return productoRepository.save(productoEntity.get());
        } else {
            return MessageAplication.INSUFFICIENTBALANCE;
        }
    }

    @Override
    public void tranferirDinero(String origenNumeroCuenta,  String destinoNumeroCuenta, BigDecimal monto) {
        retirarDinero(origenNumeroCuenta,monto);
        consignarDinero(destinoNumeroCuenta,monto);
    }


    public static String generarNumeroCuentaAleatorio(String prefijo) {
        Random random = new Random();
        int numeroAleatorio = 10000000 + random.nextInt(90000000);
        return prefijo + numeroAleatorio;
    }


}
