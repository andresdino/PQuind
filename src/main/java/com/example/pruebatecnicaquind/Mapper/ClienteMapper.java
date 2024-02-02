package com.example.pruebatecnicaquind.Mapper;

import com.example.pruebatecnicaquind.Dto.ClienteDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;

public class ClienteMapper {
    public static ClienteEntity dtoToClienteEntity(ClienteDto clienteDTO) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setTipoIdentificacion(clienteDTO.getTipoIdentificacion());
        clienteEntity.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
        clienteEntity.setNombre(clienteDTO.getNombre());
        clienteEntity.setApellido(clienteDTO.getApellido());
        clienteEntity.setEmail(clienteDTO.getEmail());
        clienteEntity.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        clienteEntity.setFechaCreacion(clienteDTO.getFechaCreacion());
        clienteEntity.setFechaModificacion(clienteDTO.getFechaModificacion());

        return clienteEntity;
    }
}
