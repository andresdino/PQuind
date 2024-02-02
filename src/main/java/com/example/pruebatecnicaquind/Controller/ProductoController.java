package com.example.pruebatecnicaquind.Controller;


import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Dto.ProductoDto;
import com.example.pruebatecnicaquind.Dto.RequestCuentaClienteDto;
import com.example.pruebatecnicaquind.Service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/apí/v1/producto")
public class ProductoController {

    @Autowired
    private IProductoService iProductoService;


    @PostMapping("/createCuenta")
    public ResponseEntity<ProductoDto> createCuenta(@RequestBody RequestCuentaClienteDto requestCuentaClienteDto){
        try {
            Object nuevoClienteEntity = iProductoService.createCuenta(requestCuentaClienteDto);
            return new ResponseEntity(nuevoClienteEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Error al crear el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/updateEstadoCuenta")
    public ResponseEntity<Object> updateEstadoCuenta(@RequestBody EditarEstadoCuentaDto editarEstadoCuentaDto) {
        try {
            Object resultado = iProductoService.updateEstadoCuenta(editarEstadoCuentaDto);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el estado de la cuenta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/cancelarCuenta")
    public ResponseEntity<Object> cancelarCuenta(@RequestBody EditarEstadoCuentaDto editarEstadoCuentaDto) {
        try {
            Object resultado = iProductoService.cancelarCuenta(editarEstadoCuentaDto);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al cancelar la cuenta de ahorro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/consignar/{numeroCuenta}")
    public ResponseEntity<Object> consignar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        try {
            iProductoService.consignarDinero(numeroCuenta, monto);
            return new ResponseEntity<>("Se cosnigno",HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cuenta de ahorro con ID no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al consignar en la cuenta de ahorro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/retirar/{numeroCuenta}")
    public ResponseEntity<Void> retirar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        try {
            iProductoService.retirarDinero(numeroCuenta, monto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cuenta de ahorro con ID no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al retirar de la cuenta de ahorro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transferir")
    public ResponseEntity<Void> transferir(@RequestParam String origenCuentaAhorronumeroCuenta, @RequestParam String destinoCuentaAhorronumeroCuenta, @RequestParam BigDecimal monto) {
        try {
            iProductoService.tranferirDinero(origenCuentaAhorronumeroCuenta, destinoCuentaAhorronumeroCuenta, monto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cuenta de ahorro con ID no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al transferir en la cuenta de ahorro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
