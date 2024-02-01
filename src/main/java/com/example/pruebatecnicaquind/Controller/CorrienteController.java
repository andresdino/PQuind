package com.example.pruebatecnicaquind.Controller;

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
    private ICuentaCorrienteService cuentaCorrienteService;

    @Autowired
    private ClienteService clienteService;

    // Endpoints para Corriente

    @PostMapping("/crear/{clienteId}")
    public ResponseEntity<CuentaCorrienteEntity> crearCuentaCorriente(@PathVariable Long clienteId) {
        try {
            ClienteEntity clienteEntity = clienteService.getClienteById(clienteId);
            CuentaCorrienteEntity cuentaCorrienteEntity = cuentaCorrienteService.crearCuentaCorriente(clienteEntity);
            return new ResponseEntity<>(cuentaCorrienteEntity, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al crear la cuenta corriente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/cancelarCuentaCorriente")
    public Object updateEstadoCuenta(@RequestBody EditarEstadoCuentaDto editarEstadoCuentaDto) {
        return cuentaCorrienteService.updateEstadoCuenta(editarEstadoCuentaDto);
    }

    @PatchMapping("/cancelarCuentaCorriente")
    public Object cancelarCuentaCorriente(@RequestBody EditarEstadoCuentaDto editarEstadoCuentaDto) {
        return cuentaCorrienteService.cancelarCuentaCorriente(editarEstadoCuentaDto);
    }

    @PostMapping("/consignar/{cuentaCorrienteId}")
    public ResponseEntity<Void> consignar(@PathVariable String cuentaCorrienteId, @RequestParam BigDecimal monto) {
        try {
            cuentaCorrienteService.consignar(cuentaCorrienteId, monto);
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
            cuentaCorrienteService.retirar(cuentaCorrienteId, monto);
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
            cuentaCorrienteService.transferir(origenCuentaCorrienteId, destinoCuentaCorrienteId, monto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cuenta corriente con ID no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al transferir en la cuenta corriente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
