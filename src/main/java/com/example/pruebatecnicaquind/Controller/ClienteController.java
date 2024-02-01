package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Entity.Cliente;
import com.example.pruebatecnicaquind.Entity.Dto.ClienteDTO;
import com.example.pruebatecnicaquind.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Endpoints para Cliente

    @PostMapping("/crear")
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody Cliente cliente){
        try {
            Cliente nuevoCliente = clienteService.createCliente(cliente);
            return new ResponseEntity(nuevoCliente, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Error al crear el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteActualizado = clienteService.updateCliente(id, cliente);
            return new ResponseEntity(clienteActualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al actualizar el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        try {
            clienteService.deleteCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al eliminar el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        try {
            List<Cliente> clientes = clienteService.getAllClientes();
            return new ResponseEntity(clientes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error al obtener la lista de clientes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.getClienteById(id);
            return new ResponseEntity(cliente, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al obtener el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
