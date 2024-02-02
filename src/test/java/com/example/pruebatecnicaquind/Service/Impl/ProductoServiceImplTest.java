package com.example.pruebatecnicaquind.Service.Impl;

import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import com.example.pruebatecnicaquind.Enum.EstadoCuenta;
import com.example.pruebatecnicaquind.Repository.CuentaRepository;
import com.example.pruebatecnicaquind.Repository.ProductoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private CuentaAhorroImpl cuentaAhorro;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createCuenta() {

    }

    @Test
    void updateEstadoCuenta() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();
        editarEstadoCuentaDto.setNumeroCuenta("123");
        editarEstadoCuentaDto.setEstado(EstadoCuenta.ACTIVA);

        ProductoEntity productoEntity = new ProductoEntity();

        Mockito.when(productoRepository.findProductoEntityByNumeroCuenta(Mockito.anyString()))
                .thenReturn(Optional.of(productoEntity));

        Mockito.when(productoRepository.save(productoEntity))
                .thenReturn(productoEntity);

        Object result = productoService.updateEstadoCuenta(editarEstadoCuentaDto);
        Assertions.assertNotNull(result);
    }

    @Test
    void updateEstadoCuentaNotFound() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();
        editarEstadoCuentaDto.setNumeroCuenta("123");

        Mockito.when(productoRepository.findProductoEntityByNumeroCuenta(Mockito.anyString()))
                .thenReturn(Optional.empty());

        productoService.updateEstadoCuenta(editarEstadoCuentaDto);

    }

    @Test
    void cancelarCuentaSucces() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();
        editarEstadoCuentaDto.setNumeroCuenta("123");

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setSaldo(BigDecimal.valueOf(0.00));

        Mockito.when(productoRepository.findProductoEntityByNumeroCuenta(Mockito.anyString()))
                .thenReturn(Optional.of(productoEntity));

        Mockito.when(productoRepository.save(productoEntity))
                .thenReturn(productoEntity);

        productoService.cancelarCuenta(editarEstadoCuentaDto);
    }

    @Test
    void cancelarCuentaSaldoMayor() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();
        editarEstadoCuentaDto.setNumeroCuenta("123");

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setSaldo(BigDecimal.valueOf(1000.00));

        Mockito.when(productoRepository.findProductoEntityByNumeroCuenta(Mockito.anyString()))
                .thenReturn(Optional.of(productoEntity));

        productoService.cancelarCuenta(editarEstadoCuentaDto);
    }

    @Test
    void cancelarCuentaNotFound() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();
        editarEstadoCuentaDto.setNumeroCuenta("123");

        Mockito.when(productoRepository.findProductoEntityByNumeroCuenta(Mockito.anyString()))
                .thenReturn(Optional.empty());

        productoService.cancelarCuenta(editarEstadoCuentaDto);
    }

    @Test
    void consignarDineroSucces() {
        String numeroCuenta = "123";
        BigDecimal monto = BigDecimal.valueOf(1000.000);

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setSaldo(BigDecimal.valueOf(1000.00));

        Mockito.when(productoRepository.findProductoEntityByNumeroCuenta(Mockito.anyString()))
                .thenReturn(Optional.of(productoEntity));

        Mockito.when(productoRepository.save(productoEntity))
                .thenReturn(productoEntity);

        productoService.consignarDinero(numeroCuenta, monto);
    }

    @Test
    void consignarDineroNotFound() {
        String numeroCuenta = "123";
        BigDecimal monto = BigDecimal.valueOf(1000.000);

        ProductoEntity productoEntity = new ProductoEntity();

        Mockito.when(productoRepository.findProductoEntityByNumeroCuenta(Mockito.anyString()))
                .thenReturn(Optional.empty());

        productoService.consignarDinero(numeroCuenta, monto);
    }

    @Test
    void retirarDineroSucces() {
        String numeroCuenta = "123";
        BigDecimal monto = BigDecimal.valueOf(1000.000);

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setSaldo(BigDecimal.valueOf(5000.000));

        Mockito.when(productoRepository.findProductoEntityByNumeroCuenta(Mockito.anyString()))
                .thenReturn(Optional.of(productoEntity));

        Mockito.when(productoRepository.save(productoEntity))
                .thenReturn(productoEntity);

        productoService.retirarDinero(numeroCuenta,monto);
    }

    @Test
    void retirarDineroNotFound() {
        String numeroCuenta = "123";
        BigDecimal monto = BigDecimal.valueOf(1000.000);

        Mockito.when(productoRepository.findProductoEntityByNumeroCuenta(Mockito.anyString()))
                .thenReturn(Optional.empty());

        productoService.retirarDinero(numeroCuenta,monto);
    }

    @Test
    void retirarDineroInsuficiente() {
        String numeroCuenta = "123";
        BigDecimal monto = BigDecimal.valueOf(1000.000);

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setSaldo(BigDecimal.valueOf(100.00));

        Mockito.when(productoRepository.findProductoEntityByNumeroCuenta(Mockito.anyString()))
                .thenReturn(Optional.of(productoEntity));

        productoService.retirarDinero(numeroCuenta,monto);
    }

    @Test
    void tranferirDinero() {
        String origenNumeroCuenta = "123";
        String destinoNumeroCuenta = "1234";
        BigDecimal monto = BigDecimal.valueOf(1000.000);

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setSaldo(BigDecimal.valueOf(5000.000));

        Mockito.when(productoRepository.findProductoEntityByNumeroCuenta(Mockito.anyString()))
                .thenReturn(Optional.of(productoEntity));

        Mockito.when(productoRepository.findProductoEntityByNumeroCuenta(Mockito.anyString()))
                .thenReturn(Optional.of(productoEntity));

        Mockito.when(productoRepository.save(productoEntity))
                .thenReturn(productoEntity);

        productoService.tranferirDinero(origenNumeroCuenta,destinoNumeroCuenta, monto);

    }

    @Test
    void generarNumeroUnico() {
    }

    @Test
    void estadoCuenta() {
    }
}