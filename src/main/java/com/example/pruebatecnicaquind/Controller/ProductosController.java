package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Dto.ProductoDTO;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import com.example.pruebatecnicaquind.Service.Implementation.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private IProductoService iProductoService;

    // Endpoints para Producto

    @PostMapping("/crear")
    public ResponseEntity<ProductoEntity> createProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            ProductoEntity nuevoProductoEntity = iProductoService.createProducto(productoDTO);
            return new ResponseEntity<>(nuevoProductoEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Error al crear el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transaccion/{productoId}")
    public ResponseEntity<String> realizarTransaccion(@PathVariable Long productoId, @RequestParam BigDecimal monto) {
        try {
            iProductoService.realizarTransaccion(productoId, monto);
            return new ResponseEntity<>("Transacción realizada con éxito", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Producto no encontrado o monto inválido", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al realizar la transacción", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/consignar/{productoId}")
    public ResponseEntity<String> consignar(@PathVariable Long productoId, @RequestParam BigDecimal monto) {
        try {
            iProductoService.consignar(productoId, monto);
            return new ResponseEntity<>("Consignación realizada con éxito", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Producto no encontrado o monto inválido", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al realizar la consignación", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/retirar/{productoId}")
    public ResponseEntity<String> retirar(@PathVariable Long productoId, @RequestParam BigDecimal monto) {
        try {
            iProductoService.retirar(productoId, monto);
            return new ResponseEntity<>("Retiro realizado con éxito", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Producto no encontrado o monto inválido", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al realizar el retiro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transferir")
    public ResponseEntity<String> transferir(@RequestParam Long origenProductoId, @RequestParam Long destinoProductoId, @RequestParam BigDecimal monto) {
        try {
            iProductoService.transferir(origenProductoId, destinoProductoId, monto);
            return new ResponseEntity<>("Transferencia realizada con éxito", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Productos no encontrados o monto inválido", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al realizar la transferencia", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cancelar-cuenta/{productoId}")
    public ResponseEntity<String> cancelarCuenta(@PathVariable Long productoId) {
        try {
            iProductoService.cancelarCuenta(productoId);
            return new ResponseEntity<>("Cuenta cancelada con éxito", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al cancelar la cuenta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

