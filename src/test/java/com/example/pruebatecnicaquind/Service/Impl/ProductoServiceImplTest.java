package com.example.pruebatecnicaquind.Service.Impl;

import com.example.pruebatecnicaquind.Constans.MessageAplication;
import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Dto.ProductoDto;
import com.example.pruebatecnicaquind.Dto.RequestCuentaClienteDto;
import com.example.pruebatecnicaquind.Entity.CuentaEntity;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import com.example.pruebatecnicaquind.Enum.EstadoCuenta;
import com.example.pruebatecnicaquind.Enum.TipoCuenta;
import com.example.pruebatecnicaquind.Repository.CuentaRepository;
import com.example.pruebatecnicaquind.Repository.ProductoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Mock
    private CuentaImpl cuentaServiceMock;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    public void createCuentaAhorros() {
    }

    @Test
    void createCuentaCorriente() {
    }


    @Test
    void updateEstadoCuenta() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();
        editarEstadoCuentaDto.setNumeroCuenta("123");
        editarEstadoCuentaDto.setEstado(EstadoCuenta.ACTIVA);

        ProductoEntity productoEntity = new ProductoEntity();

        when(productoRepository.findProductoEntityByNumeroCuenta(anyString()))
                .thenReturn(Optional.of(productoEntity));

        when(productoRepository.save(productoEntity))
                .thenReturn(productoEntity);

        Object result = productoService.updateEstadoCuenta(editarEstadoCuentaDto);
        assertNotNull(result);
    }

    @Test
    void updateEstadoCuentaNotFound() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();
        editarEstadoCuentaDto.setNumeroCuenta("123");

        when(productoRepository.findProductoEntityByNumeroCuenta(anyString()))
                .thenReturn(Optional.empty());

        productoService.updateEstadoCuenta(editarEstadoCuentaDto);

    }

    @Test
    void cancelarCuentaSucces() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();
        editarEstadoCuentaDto.setNumeroCuenta("123");

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setSaldo(BigDecimal.valueOf(0.00));

        when(productoRepository.findProductoEntityByNumeroCuenta(anyString()))
                .thenReturn(Optional.of(productoEntity));

        when(productoRepository.save(productoEntity))
                .thenReturn(productoEntity);

        productoService.cancelarCuenta(editarEstadoCuentaDto);
    }

    @Test
    void cancelarCuentaSaldoMayor() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();
        editarEstadoCuentaDto.setNumeroCuenta("123");

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setSaldo(BigDecimal.valueOf(1000.00));

        when(productoRepository.findProductoEntityByNumeroCuenta(anyString()))
                .thenReturn(Optional.of(productoEntity));

        productoService.cancelarCuenta(editarEstadoCuentaDto);
    }

    @Test
    void cancelarCuentaNotFound() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();
        editarEstadoCuentaDto.setNumeroCuenta("123");

        when(productoRepository.findProductoEntityByNumeroCuenta(anyString()))
                .thenReturn(Optional.empty());

        productoService.cancelarCuenta(editarEstadoCuentaDto);
    }

    @Test
    void consignarDineroSucces() {
        String numeroCuenta = "123";
        BigDecimal monto = BigDecimal.valueOf(1000.000);

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setSaldo(BigDecimal.valueOf(1000.00));

        when(productoRepository.findProductoEntityByNumeroCuenta(anyString()))
                .thenReturn(Optional.of(productoEntity));

        when(productoRepository.save(productoEntity))
                .thenReturn(productoEntity);

        productoService.consignarDinero(numeroCuenta, monto);
    }

    @Test
    void consignarDineroNotFound() {
        // Arrange
        String numeroCuenta = "NUMERO_DE_PRUEBA";
        BigDecimal monto = BigDecimal.ZERO;

        productoService.consignarDinero(numeroCuenta, monto);
    }

    @Test
    void consignarDineroAccountNotFound() {
        String numeroCuenta = "CUENTA_NO_EXISTENTE";
        BigDecimal monto = BigDecimal.TEN;

        when(productoRepository.findProductoEntityByNumeroCuenta(numeroCuenta)).thenReturn(Optional.empty());

        productoService.consignarDinero(numeroCuenta, monto);

    }

    @Test
    void retirarDineroSucces() {
        String numeroCuenta = "123";
        BigDecimal monto = BigDecimal.valueOf(1000.000);

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setSaldo(BigDecimal.valueOf(5000.000));

        when(productoRepository.findProductoEntityByNumeroCuenta(anyString()))
                .thenReturn(Optional.of(productoEntity));

        when(productoRepository.save(productoEntity))
                .thenReturn(productoEntity);

        productoService.retirarDinero(numeroCuenta,monto);
    }

    @Test
    void retirarDineroNotFound() {
        String numeroCuenta = "123";
        BigDecimal monto = BigDecimal.valueOf(1000.000);

        when(productoRepository.findProductoEntityByNumeroCuenta(anyString()))
                .thenReturn(Optional.empty());

        productoService.retirarDinero(numeroCuenta,monto);
    }

    @Test
    void retirarDineroInsuficiente() {
        String numeroCuenta = "123";
        BigDecimal monto = BigDecimal.valueOf(1000.000);

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setSaldo(BigDecimal.valueOf(100.00));

        when(productoRepository.findProductoEntityByNumeroCuenta(anyString()))
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

        when(productoRepository.findProductoEntityByNumeroCuenta(anyString()))
                .thenReturn(Optional.of(productoEntity));

        when(productoRepository.findProductoEntityByNumeroCuenta(anyString()))
                .thenReturn(Optional.of(productoEntity));

        when(productoRepository.save(productoEntity))
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