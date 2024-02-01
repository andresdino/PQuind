package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Entity.Cliente;
import com.example.pruebatecnicaquind.Entity.Cuentas.CuentaCorriente;
import com.example.pruebatecnicaquind.Entity.Dto.ClienteDTO;
import com.example.pruebatecnicaquind.Service.ClienteService;
import com.example.pruebatecnicaquind.Service.CuentaCorrienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/corriente")
public class CorrienteController {

    @Autowired
    private CuentaCorrienteService cuentaCorrienteService;

    @Autowired
    private ClienteService clienteService;

    // Endpoints para Corriente

    @PostMapping("/crear/{clienteId}")
    public ResponseEntity<CuentaCorriente> crearCuentaCorriente(@PathVariable Long clienteId) {
        try {
            Cliente cliente = clienteService.getClienteById(clienteId);
            CuentaCorriente cuentaCorriente = cuentaCorrienteService.crearCuentaCorriente(cliente);
            return new ResponseEntity<>(cuentaCorriente, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al crear la cuenta corriente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/consignar/{cuentaCorrienteId}")
    public ResponseEntity<Void> consignar(@PathVariable Long cuentaCorrienteId, @RequestParam BigDecimal monto) {
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
    public ResponseEntity<Void> retirar(@PathVariable Long cuentaCorrienteId, @RequestParam BigDecimal monto) {
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
    public ResponseEntity<Void> transferir(@RequestParam Long origenCuentaCorrienteId, @RequestParam Long destinoCuentaCorrienteId, @RequestParam BigDecimal monto) {
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
