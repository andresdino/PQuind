package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Dto.ClienteDTO;
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

    @PostMapping("/crear")
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO){
        try {
            ClienteEntity nuevoClienteEntity = clienteService.createCliente(clienteDTO);
            return new ResponseEntity(nuevoClienteEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Error al crear el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<ClienteEntity> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteEntity clienteEntityActualizado = clienteService.updateCliente(id, clienteDTO);
            return new ResponseEntity(clienteEntityActualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al actualizar el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cliente/{id}")
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
    public ResponseEntity<List<ClienteEntity>> getAllClientes() {
        try {
            List<ClienteEntity> clienteEntities = clienteService.getAllClientes();
            return new ResponseEntity(clienteEntities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error al obtener la lista de clientes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteEntity> getClienteById(@PathVariable Long id) {
        try {
            ClienteEntity clienteEntity = clienteService.getClienteById(id);
            return new ResponseEntity(clienteEntity, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al obtener el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
