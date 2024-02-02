package com.example.pruebatecnicaquind.Service;

import com.example.pruebatecnicaquind.Dto.ClienteDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;

import java.util.List;

public interface IClienteService {

    List<ClienteEntity> getAllClientes();

    public ClienteEntity getClienteById(Long id);

    Object createCliente(ClienteDto clienteDTO);

    Object updateCliente(String numeroIdentificacion, ClienteDto clienteDTO);

    String deleteCliente(String numeroIdentificacion);

}
