package com.example.pruebatecnicaquind.Service.Implementation;

import com.example.pruebatecnicaquind.Entity.Cliente;

import java.util.List;

public interface IClienteService {

    public Cliente createCliente(Cliente cliente);

    public Cliente updateCliente(Long id, Cliente cliente);

    public void deleteCliente(Long id);

    public List<Cliente> getAllClientes();

    public Cliente getClienteById(Long id);

}
