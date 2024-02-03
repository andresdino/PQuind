package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Constans.MessageAplication;
import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Dto.RequestCuentaClienteDto;
import com.example.pruebatecnicaquind.Service.IProductoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductoControllerTest {

    @InjectMocks
    private ProductoController productoController;

    @Mock
    private IProductoService iProductoService;

    @Test
    void createCuenta_Success() {
        RequestCuentaClienteDto requestCuentaClienteDto = new RequestCuentaClienteDto();

        when(iProductoService.createCuenta(any(RequestCuentaClienteDto.class))).thenReturn(true);

        productoController.createCuenta(requestCuentaClienteDto);
    }

    @Test
    void createCuenta_Failure() {
        RequestCuentaClienteDto requestCuentaClienteDto = new RequestCuentaClienteDto();

        when(iProductoService.createCuenta(any(RequestCuentaClienteDto.class))).thenThrow(new RuntimeException(MessageAplication.SIMULATEDERROR));

        productoController.createCuenta(requestCuentaClienteDto);
    }

    @Test
    void updateEstadoCuenta_Success() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();

        when(iProductoService.updateEstadoCuenta(any())).thenReturn(null);

        productoController.updateEstadoCuenta(editarEstadoCuentaDto);
    }

    @Test
    void updateEstadoCuenta_AccountNotFound() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();

        when(iProductoService.updateEstadoCuenta(any())).thenThrow(new IllegalArgumentException(MessageAplication.ACCOUNTNOTFOUND));

        productoController.updateEstadoCuenta(editarEstadoCuentaDto);
    }

    @Test
    void cancelarCuenta_Success() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();

        when(iProductoService.cancelarCuenta(any())).thenReturn(null);

        productoController.cancelarCuenta(editarEstadoCuentaDto);
    }

    @Test
    void cancelarCuenta_Exception() {
        EditarEstadoCuentaDto editarEstadoCuentaDto = new EditarEstadoCuentaDto();

        when(iProductoService.cancelarCuenta(any())).thenThrow(new RuntimeException(MessageAplication.ACCOUNTCANNOTCANCELLED));

        productoController.cancelarCuenta(editarEstadoCuentaDto);
    }

    @Test
    void consignar_Success() {
        String numeroCuenta = "123456789";
        BigDecimal monto = BigDecimal.valueOf(100);

        when(iProductoService.consignarDinero(any(), any())).thenReturn(null);

        productoController.consignar(numeroCuenta, monto);
    }

    @Test
    void consignar_AccountNotFound() {
        String numeroCuenta = "123456789";
        BigDecimal monto = BigDecimal.valueOf(100);

        when(iProductoService.consignarDinero(any(), any())).thenThrow(new IllegalArgumentException(MessageAplication.ACCOUNTNOTFOUND));

        productoController.consignar(numeroCuenta, monto);

    }

    @Test
    void consignar_Exception() {
        String numeroCuenta = "123456789";
        BigDecimal monto = BigDecimal.valueOf(100);

        when(iProductoService.consignarDinero(any(), any())).thenThrow(new RuntimeException(MessageAplication.CANNOTOBTAINED));

        productoController.consignar(numeroCuenta, monto);

    }

    @Test
    void retirar_Success() {
        String numeroCuenta = "123456789";
        BigDecimal monto = BigDecimal.valueOf(100);

        when(iProductoService.retirarDinero(any(), any())).thenReturn(null);

        productoController.retirar(numeroCuenta, monto);

    }

    @Test
    void retirar_AccountNotFound() {
        String numeroCuenta = "123456789";
        BigDecimal monto = BigDecimal.valueOf(100);

        when(iProductoService.retirarDinero(any(), any())).thenThrow(new IllegalArgumentException(MessageAplication.ACCOUNTNOTFOUND));

        productoController.retirar(numeroCuenta, monto);

    }

    @Test
    void retirar_Exception() {
        String numeroCuenta = "123456789";
        BigDecimal monto = BigDecimal.valueOf(100);

        when(iProductoService.retirarDinero(any(), any())).thenThrow(new RuntimeException(MessageAplication.CANNOTWITHDRAWN));

        productoController.retirar(numeroCuenta, monto);

    }


    @Test
    void transferir_Success() {
        String origenCuenta = "123456789";
        String destinoCuenta = "987654321";
        BigDecimal monto = BigDecimal.valueOf(100);

        doAnswer(invocation -> {
            return "Transferencia exitosa";
        }).when(iProductoService).tranferirDinero(any(), any(), any());

        productoController.transferir(origenCuenta, destinoCuenta, monto);
    }




    @Test
    void transferir_AccountNotFound() {
        // Configuración del escenario
        String origenCuenta = "123456789";
        String destinoCuenta = "987654321";
        BigDecimal monto = BigDecimal.valueOf(100);

        // Configuración específica para la prueba
        doThrow(new IllegalArgumentException("Cuenta no encontrada")).when(iProductoService).tranferirDinero(any(), any(), any());

        // Llamada al método que queremos probar
        String result = productoController.transferir(origenCuenta, destinoCuenta, monto);

        // Verificación
        assertEquals(MessageAplication.ACCOUNTNOTFOUND, result);
    }

    @Test
    void transferir_Exception() {
        // Configuración del escenario
        String origenCuenta = "123456789";
        String destinoCuenta = "987654321";
        BigDecimal monto = BigDecimal.valueOf(100);

        // Configuración específica para la prueba
        doThrow(new RuntimeException("Error al transferir en la cuenta")).when(iProductoService).tranferirDinero(any(), any(), any());

        // Llamada al método que queremos probar
        String result = productoController.transferir(origenCuenta, destinoCuenta, monto);

        // Verificación
        assertEquals(MessageAplication.ACCOUNTCANCELLED, result);
    }
}
