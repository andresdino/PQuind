package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Dto.ClienteDto;

import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Repository.ProductoRepository;
import com.example.pruebatecnicaquind.Service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/apí/v1/cliente")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @PostMapping("/create")
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDTO){
        try {
            Object nuevoClienteEntity = iClienteService.createCliente(clienteDTO);
            return new ResponseEntity(nuevoClienteEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Error al crear el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/update/{numeroIdentificacion}")
    public ResponseEntity<ClienteEntity> updateCliente(@PathVariable String numeroIdentificacion, @RequestBody ClienteDto clienteDTO) {
        try {
            Object clienteEntityActualizado = iClienteService.updateCliente(numeroIdentificacion, clienteDTO);
            return new ResponseEntity(clienteEntityActualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al actualizar el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{numeroIdentificacion}")
    public ResponseEntity<String> deleteCliente(@PathVariable String numeroIdentificacion) {
        try {
            String mensaje = iClienteService.deleteCliente(numeroIdentificacion);
            return new ResponseEntity<>(mensaje,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity("Cliente con ID no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Error al eliminar el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
