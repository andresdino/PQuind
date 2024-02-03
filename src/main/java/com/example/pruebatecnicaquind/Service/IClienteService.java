package com.example.pruebatecnicaquind.Service;

import com.example.pruebatecnicaquind.Dto.ClienteDto;

public interface IClienteService {

    Object createCliente(ClienteDto clienteDTO);

    Object updateCliente(String numeroIdentificacion, ClienteDto clienteDTO);

    String deleteCliente(String numeroIdentificacion);

}
