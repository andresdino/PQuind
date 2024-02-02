package com.example.pruebatecnicaquind.Service.Impl;

import com.example.pruebatecnicaquind.Constans.MessageAplication;
import com.example.pruebatecnicaquind.Dto.RequestCuentaClienteDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Entity.CuentaEntity;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import com.example.pruebatecnicaquind.Enum.EstadoCuenta;
import com.example.pruebatecnicaquind.Mapper.ProductoMapper;
import com.example.pruebatecnicaquind.Repository.CuentaRepository;
import com.example.pruebatecnicaquind.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaAhorroImpl {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public Object crearCuentaAhorro(RequestCuentaClienteDto requestCuentaClienteDto) {

        if (requestCuentaClienteDto.getProductoDto().getSaldo().compareTo(BigDecimal.ZERO) < 0){
            return MessageAplication.BALANCECANNOTLESS0;
        }

        requestCuentaClienteDto.getProductoDto().setNumeroCuenta(null);
        String numeroCuenta;

        numeroCuenta = String.format("53%08d",4);
        requestCuentaClienteDto.getProductoDto().setNumeroCuenta(numeroCuenta);
        requestCuentaClienteDto.getProductoDto().setEstado(EstadoCuenta.ACTIVA);

        ProductoEntity producto = ProductoMapper.dtoToProductoEntity(requestCuentaClienteDto.getProductoDto());
        productoRepository.save(producto);

        CuentaEntity cuentaEntity = new CuentaEntity();
        cuentaEntity.setTipoCuenta(requestCuentaClienteDto.getTipoCuenta());
        cuentaEntity.setProductoEntity(producto);
        cuentaRepository.save(cuentaEntity);

        return "se guardo bien";
    }
}
