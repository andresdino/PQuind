package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Dto.ClienteDto;

import com.example.pruebatecnicaquind.Service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
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
}
