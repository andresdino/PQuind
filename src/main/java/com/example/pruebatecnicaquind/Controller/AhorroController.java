package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Entity.Cliente;
import com.example.pruebatecnicaquind.Entity.Cuentas.CuentaAhorro;
import com.example.pruebatecnicaquind.Entity.Dto.ClienteDTO;
import com.example.pruebatecnicaquind.Service.ClienteService;
import com.example.pruebatecnicaquind.Service.CuentaAhorroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/ahorro")
public class AhorroController {

    @Autowired
    private CuentaAhorroService cuentaAhorroService;

    @Autowired
    private ClienteService clienteService;

    // Endpoints para Ahorro

    @PostMapping("/crear/{clienteId}")
    public ResponseEntity<CuentaAhorro> crearCuentaAhorro(@PathVariable Long clienteId) {
        try {
            Cliente cliente = clienteService.getClienteById(clienteId);
            CuentaAhorro cuentaAhorro = cuentaAhorroService.crearCuentaAhorro(cliente);
            return new ResponseEntity<>(cuentaAhorro, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al crear la cuenta de ahorro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/consignar/{cuentaAhorroId}")
    public ResponseEntity<Void> consignar(@PathVariable Long cuentaAhorroId, @RequestParam BigDecimal monto) {
        try {
            cuentaAhorroService.consignar(cuentaAhorroId, monto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cuenta de ahorro con ID no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al consignar en la cuenta de ahorro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/retirar/{cuentaAhorroId}")
    public ResponseEntity<Void> retirar(@PathVariable Long cuentaAhorroId, @RequestParam BigDecimal monto) {
        try {
            cuentaAhorroService.retirar(cuentaAhorroId, monto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cuenta de ahorro con ID no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al retirar de la cuenta de ahorro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transferir")
    public ResponseEntity<Void> transferir(@RequestParam Long origenCuentaAhorroId, @RequestParam Long destinoCuentaAhorroId, @RequestParam BigDecimal monto) {
        try {
            cuentaAhorroService.transferir(origenCuentaAhorroId, destinoCuentaAhorroId, monto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cuenta de ahorro con ID no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al transferir en la cuenta de ahorro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
