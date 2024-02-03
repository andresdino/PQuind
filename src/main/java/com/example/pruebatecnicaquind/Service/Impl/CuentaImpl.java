package com.example.pruebatecnicaquind.Service.Impl;

import com.example.pruebatecnicaquind.Constans.MessageAplication;
import com.example.pruebatecnicaquind.Dto.RequestCuentaClienteDto;
import com.example.pruebatecnicaquind.Entity.CuentaEntity;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import com.example.pruebatecnicaquind.Enum.EstadoCuenta;
import com.example.pruebatecnicaquind.Mapper.ProductoMapper;
import com.example.pruebatecnicaquind.Repository.CuentaRepository;
import com.example.pruebatecnicaquind.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class CuentaImpl {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public Object crearCuentaAhorro(RequestCuentaClienteDto requestCuentaClienteDto) {

        if (requestCuentaClienteDto.getProductoDto().getSaldo().compareTo(BigDecimal.ZERO) < 0){
            return MessageAplication.BALANCECANNOTLESS0;
        }

        requestCuentaClienteDto.getProductoDto().setNumeroCuenta(null);

        requestCuentaClienteDto.getProductoDto().setNumeroCuenta(generarNumeroCuentaAleatorio("53"));
        requestCuentaClienteDto.getProductoDto().setEstado(EstadoCuenta.ACTIVA);
        requestCuentaClienteDto.getProductoDto().setFechaCreacion(LocalDateTime.now());

        ProductoEntity producto = ProductoMapper.dtoToProductoEntity(requestCuentaClienteDto.getProductoDto());
        productoRepository.save(producto);

        CuentaEntity cuentaEntity = new CuentaEntity();
        cuentaEntity.setTipoCuenta(requestCuentaClienteDto.getTipoCuenta());
        cuentaEntity.setProductoEntity(producto);
        cuentaRepository.save(cuentaEntity);

        return MessageAplication.ACCOUNTCREATED;
    }

    public Object crearCuentaCorriente(RequestCuentaClienteDto requestCuentaClienteDto) {

        requestCuentaClienteDto.getProductoDto().setNumeroCuenta(null);

        requestCuentaClienteDto.getProductoDto().setNumeroCuenta(generarNumeroCuentaAleatorio("33"));
        requestCuentaClienteDto.getProductoDto().setEstado(requestCuentaClienteDto.getProductoDto().getEstado());
        requestCuentaClienteDto.getProductoDto().setFechaCreacion(LocalDateTime.now());

        ProductoEntity producto = ProductoMapper.dtoToProductoEntity(requestCuentaClienteDto.getProductoDto());
        productoRepository.save(producto);

        CuentaEntity cuentaEntity = new CuentaEntity();
        cuentaEntity.setTipoCuenta(requestCuentaClienteDto.getTipoCuenta());
        cuentaEntity.setProductoEntity(producto);
        cuentaRepository.save(cuentaEntity);

        return MessageAplication.ACCOUNTCREATED;
    }


    public String generarNumeroCuentaAleatorio(String prefijo) {
        Random random = new Random();
        int numeroAleatorio = 10000000 + random.nextInt(90000000);

        while(productoRepository.existsByNumeroCuenta(prefijo+numeroAleatorio)){
            numeroAleatorio = 10000000 + random.nextInt(90000000);
        }
        return prefijo + numeroAleatorio;
    }
}
