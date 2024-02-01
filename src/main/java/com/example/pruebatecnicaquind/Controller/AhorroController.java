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

@RestController
@RequestMapping("/ahorro")
public class AhorroController {

    @Autowired
    private ICuentaAhorroService iCuentaAhorroService;

    @Autowired
    private ClienteService clienteService;

    // Endpoints para Ahorro

    /*
    @PostMapping("/crear/{clienteId}")
    public ResponseEntity<CuentaAhorroEntity> crearCuentaAhorro(@PathVariable Long clienteId, @RequestBody CuentaAhorroDto cuentaAhorroDto) {
        try {
            ClienteEntity clienteEntity = clienteService.getClienteById(clienteId);
            CuentaAhorroEntity cuentaAhorroEntity = iCuentaAhorroService.crearCuentaAhorro(clienteEntity,cuentaAhorroDto);
            return new ResponseEntity<>(cuentaAhorroEntity, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al crear la cuenta de ahorro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     */

    @PostMapping("/crear/{clienteId}")
    public ResponseEntity<Object> crearCuentaAhorroOso(@PathVariable Long clienteId, @RequestBody CuentaAhorroDto cuentaAhorroDto) {
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

    @PostMapping("/consignar/{cuentaAhorroId}")
    public ResponseEntity<Void> consignar(@PathVariable Long cuentaAhorroId, @RequestParam BigDecimal monto) {
        try {
            iCuentaAhorroService.consignar(cuentaAhorroId, monto);
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
            iCuentaAhorroService.retirar(cuentaAhorroId, monto);
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
            iCuentaAhorroService.transferir(origenCuentaAhorroId, destinoCuentaAhorroId, monto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cuenta de ahorro con ID no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al transferir en la cuenta de ahorro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/cancelarCuentaAhorro")
    public Object updateEstadoCuenta(@RequestBody EditarEstadoCuentaDto editarEstadoCuentaDto) {
        return iCuentaAhorroService.updateEstadoCuenta(editarEstadoCuentaDto);
    }

    @PatchMapping("/cancelarCuentaAhorro")
    public Object cancelarCuentaAhorro(@RequestBody EditarEstadoCuentaDto editarEstadoCuentaDto) {
        return iCuentaAhorroService.cancelarCuentaAhorro(editarEstadoCuentaDto);
    }
}
