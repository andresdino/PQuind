package com.example.pruebatecnicaquind.Service.Implementation;

import com.example.pruebatecnicaquind.Dto.ClienteDTO;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;

import java.util.List;

public interface IClienteService {

    ClienteEntity createCliente(ClienteDTO clienteDTO);

    public ClienteEntity updateCliente(Long id, ClienteDTO clienteDTO);

    public void deleteCliente(Long id);

    public List<ClienteEntity> getAllClientes();

    public ClienteEntity getClienteById(Long id);

}
