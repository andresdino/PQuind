package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Dto.CuentaCorrienteDto;
import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Entity.CuentaCorrienteEntity;
import com.example.pruebatecnicaquind.Service.ClienteService;
import com.example.pruebatecnicaquind.Service.Implementation.ICuentaCorrienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/corriente")
public class CorrienteController {

    @Autowired
    private ICuentaCorrienteService iCuentaCorrienteService;

    @Autowired
    private ClienteService clienteService;

    // Endpoints para Corriente

    @PostMapping("/crear/{clienteId}")
    public ResponseEntity<Object> crearCuentaCorriente(@PathVariable Long clienteId, @RequestBody CuentaCorrienteDto cuentaCorrienteDto) {
        try {
            ClienteEntity clienteEntity = clienteService.getClienteById(clienteId);
            Object cuentaCorrienteEntity = iCuentaCorrienteService.crearCuentaCorriente(clienteEntity, cuentaCorrienteDto);
            return new ResponseEntity<>(cuentaCorrienteEntity, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la cuenta corriente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/cancelarCuentaCorriente")
    public Object updateEstadoCuenta(@RequestBody EditarEstadoCuentaDto editarEstadoCuentaDto) {
        return iCuentaCorrienteService.updateEstadoCuenta(editarEstadoCuentaDto);
    }

    @PatchMapping("/cancelarCuentaCorriente")
    public Object cancelarCuentaCorriente(@RequestBody EditarEstadoCuentaDto editarEstadoCuentaDto) {
        return iCuentaCorrienteService.cancelarCuentaCorriente(editarEstadoCuentaDto);
    }

    @PostMapping("/consignar/{cuentaCorrienteId}")
    public ResponseEntity<Void> consignar(@PathVariable String cuentaCorrienteId, @RequestParam BigDecimal monto) {
        try {
            iCuentaCorrienteService.consignar(cuentaCorrienteId, monto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cuenta corriente con ID no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al consignar en la cuenta corriente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/retirar/{cuentaCorrienteId}")
    public ResponseEntity<Void> retirar(@PathVariable String cuentaCorrienteId, @RequestParam BigDecimal monto) {
        try {
            iCuentaCorrienteService.retirar(cuentaCorrienteId, monto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cuenta corriente con ID no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al retirar de la cuenta corriente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transferir")
    public ResponseEntity<Void> transferir(@RequestParam String origenCuentaCorrienteId, @RequestParam Long destinoCuentaCorrienteId, @RequestParam BigDecimal monto) {
        try {
            iCuentaCorrienteService.transferir(origenCuentaCorrienteId, destinoCuentaCorrienteId, monto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cuenta corriente con ID no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al transferir en la cuenta corriente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
