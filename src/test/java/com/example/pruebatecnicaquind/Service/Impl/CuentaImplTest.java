package com.example.pruebatecnicaquind.Service.Impl;

import com.example.pruebatecnicaquind.Dto.ProductoDto;
import com.example.pruebatecnicaquind.Dto.RequestCuentaClienteDto;
import com.example.pruebatecnicaquind.Entity.CuentaEntity;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import com.example.pruebatecnicaquind.Enum.EstadoCuenta;
import com.example.pruebatecnicaquind.Repository.CuentaRepository;
import com.example.pruebatecnicaquind.Repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CuentaImplTest {

    @InjectMocks
    private CuentaImpl cuentaImpl;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    @BeforeEach
    public void setUp() {
    }


    @Test
    void crearCuentaAhorroConSaldoNegativo() {
        RequestCuentaClienteDto requestDto = new RequestCuentaClienteDto();
        ProductoDto productoDto = new ProductoDto();
        productoDto.setSaldo(new BigDecimal("-100"));
        requestDto.setProductoDto(productoDto);

        cuentaImpl.crearCuentaAhorro(requestDto);
    }

    @Test
    void crearCuentaAhorros() {
        RequestCuentaClienteDto requestDto = new RequestCuentaClienteDto();
        requestDto.setTipoCuenta("AHORRO");
        requestDto.setProductoDto(new ProductoDto());
        requestDto.getProductoDto().setSaldo(BigDecimal.valueOf(1000));

        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(new ProductoEntity());
        when(cuentaRepository.save(any(CuentaEntity.class))).thenReturn(new CuentaEntity());

        cuentaImpl.crearCuentaAhorro(requestDto);

    }

    @Test
    void crearCuentaCorriente() {
        RequestCuentaClienteDto requestDto = new RequestCuentaClienteDto();
        requestDto.setTipoCuenta("CORRIENTE");
        requestDto.setProductoDto(new ProductoDto());
        requestDto.getProductoDto().setEstado(EstadoCuenta.ACTIVA);

        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(new ProductoEntity());

        when(cuentaRepository.save(any(CuentaEntity.class))).thenReturn(new CuentaEntity());
        cuentaImpl.crearCuentaCorriente(requestDto);

    }

    @Test
    void generarNumeroCuentaAleatorio_NumeroNoExistente() {
        when(productoRepository.existsByNumeroCuenta(any())).thenReturn(false);
        cuentaImpl.generarNumeroCuentaAleatorio("53");
    }

}
