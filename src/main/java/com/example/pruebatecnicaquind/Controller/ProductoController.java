package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Dto.RequestCuentaClienteDto;
import com.example.pruebatecnicaquind.Service.IProductoService;
import com.example.pruebatecnicaquind.Constans.MessageAplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController {

    @Autowired
    private IProductoService iProductoService;

    @PostMapping("/createCuenta")
    public String createCuenta(@RequestBody RequestCuentaClienteDto requestCuentaClienteDto){
        try {
            iProductoService.createCuenta(requestCuentaClienteDto);
            return MessageAplication.ACCOUNTCREATED;
        } catch (Exception e) {
            return MessageAplication.ACCOUNTCANCELLED;
        }
    }

    @PatchMapping("/updateEstadoCuenta")
    public String updateEstadoCuenta(@RequestBody EditarEstadoCuentaDto editarEstadoCuentaDto) {
        try {
            iProductoService.updateEstadoCuenta(editarEstadoCuentaDto);
            return MessageAplication.UPDATEACCOUNTSTATUSCORRECTLY;
        } catch (Exception e) {
            return MessageAplication.ACCOUNTNOTFOUND;
        }
    }

    @PatchMapping("/cancelarCuenta")
    public String cancelarCuenta(@RequestBody EditarEstadoCuentaDto editarEstadoCuentaDto) {
        try {
            iProductoService.cancelarCuenta(editarEstadoCuentaDto);
            return MessageAplication.ACCOUNTCANCELLED;
        } catch (Exception e) {
            return MessageAplication.ACCOUNTCANCELLED;
        }
    }

    @PostMapping("/consignar/{numeroCuenta}")
    public String consignar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        try {
            iProductoService.consignarDinero(numeroCuenta, monto);
            return MessageAplication.ACCOUNTCREATED;
        } catch (IllegalArgumentException e) {
            return MessageAplication.ACCOUNTNOTFOUND;
        } catch (Exception e) {
            return MessageAplication.ACCOUNTCANCELLED;
        }
    }

    @PostMapping("/retirar/{numeroCuenta}")
    public String retirar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        try {
            iProductoService.retirarDinero(numeroCuenta, monto);
            return MessageAplication.ACCOUNTCANCELLED;
        } catch (IllegalArgumentException e) {
            return MessageAplication.ACCOUNTNOTFOUND;
        } catch (Exception e) {
            return MessageAplication.ACCOUNTCANCELLED;
        }
    }

    @PostMapping("/transferir")
    public String transferir(@RequestParam String origenCuenta, @RequestParam String destinoCuenta, @RequestParam BigDecimal monto) {
        try {
            iProductoService.tranferirDinero(origenCuenta, destinoCuenta, monto);
            return MessageAplication.ACCOUNTCANCELLED;
        } catch (IllegalArgumentException e) {
            return MessageAplication.ACCOUNTNOTFOUND;
        } catch (Exception e) {
            return MessageAplication.ACCOUNTCANCELLED;
        }
    }
}
