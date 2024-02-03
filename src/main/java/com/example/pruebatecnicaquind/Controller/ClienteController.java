package com.example.pruebatecnicaquind.Controller;

import com.example.pruebatecnicaquind.Constans.MessageAplication;
import com.example.pruebatecnicaquind.Dto.ClienteDto;
import com.example.pruebatecnicaquind.Service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @PostMapping("/create")
    public String createCliente(@RequestBody ClienteDto clienteDTO){
        try {
            iClienteService.createCliente(clienteDTO);
            return MessageAplication.ACCOUNTCREATED;
        } catch (Exception e) {
            return MessageAplication.ACCOUNTCANNOTCANCELLED;
        }
    }

    @PatchMapping("/update/{numeroIdentificacion}")
    public String updateCliente(@PathVariable String numeroIdentificacion, @RequestBody ClienteDto clienteDTO){
        try {
            iClienteService.updateCliente(numeroIdentificacion, clienteDTO);
            return MessageAplication.UPDATEACCOUNTSTATUSCORRECTLY;
        } catch (IllegalArgumentException e) {
            return MessageAplication.ACCOUNTNOTFOUND;
        } catch (Exception e) {
            return MessageAplication.ACCOUNTCANCELLED;
        }
    }

    @DeleteMapping("/delete/{numeroIdentificacion}")
    public String deleteCliente(@PathVariable String numeroIdentificacion){
        try {
            iClienteService.deleteCliente(numeroIdentificacion);
            return MessageAplication.ACCOUNTCANCELLED;
        } catch (IllegalArgumentException e) {
            return MessageAplication.ACCOUNTNOTFOUND;
        } catch (Exception e) {
            return MessageAplication.ACCOUNTCANCELLED;
        }
    }
}
