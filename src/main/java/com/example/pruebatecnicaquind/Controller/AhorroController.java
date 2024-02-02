package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Dto.EditarEstadoCuentaDto;
import com.example.pruebatecnicaquind.Dto.CuentaAhorroDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Service.ClienteService;
import com.example.pruebatecnicaquind.Service.Implementation.ICuentaAhorroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/ahorro")
public class AhorroController {

    @Autowired
    private ICuentaAhorroService iCuentaAhorroService;

    @Autowired
    private ClienteService clienteService;

    // Endpoints para Ahorro

    @PostMapping("/crear/{clienteId}")
    public ResponseEntity<Object> crearCuentaAhorro(@PathVariable Long clienteId, @RequestBody CuentaAhorroDto cuentaAhorroDto) {
        try {
            ClienteEntity clienteEntity = clienteService.getClienteById(clienteId);
            Object cuentaAhorroEntity = iCuentaAhorroService.crearCuentaAhorro(clienteEntity,cuentaAhorroDto);
            return new ResponseEntity<>(cuentaAhorroEntity, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al crear la cuenta de ahorro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/updtadeCuentaAhorro")
    public Object updateEstadoCuenta(@RequestBody EditarEstadoCuentaDto editarEstadoCuentaDto) {
        return iCuentaAhorroService.updateEstadoCuenta(editarEstadoCuentaDto);
    }

    @PatchMapping("/cancelarCuentaAhorro")
    public Object cancelarCuentaAhorro(@RequestBody EditarEstadoCuentaDto editarEstadoCuentaDto) {
        return iCuentaAhorroService.cancelarCuentaAhorro(editarEstadoCuentaDto);
    }

    @PostMapping("/consignar/{numeroCuenta}")
    public ResponseEntity<Object> consignar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        try {
            iCuentaAhorroService.consignar(numeroCuenta, monto);
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
            iCuentaAhorroService.retirar(numeroCuenta, monto);
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
            iCuentaAhorroService.transferir(origenCuentaAhorronumeroCuenta, destinoCuentaAhorronumeroCuenta, monto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cuenta de ahorro con ID no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al transferir en la cuenta de ahorro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    private static final AtomicLong contador = new AtomicLong(1);
    @GetMapping("/numero")
    public String zEstadoCuenta() {
        long siguienteNumero = contador.getAndIncrement();

        // Formatear como "53" seguido de 8 dígitos
        String numeroCuenta = String.format("53%08d", siguienteNumero);
        return numeroCuenta;
    }
}
