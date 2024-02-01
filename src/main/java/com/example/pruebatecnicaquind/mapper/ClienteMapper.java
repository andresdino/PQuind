package com.example.pruebatecnicaquind.mapper;

import com.example.pruebatecnicaquind.Dto.ClienteDTO;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;

public class ClienteMapper {

    public static ClienteEntity dtoToClienteEntity(ClienteDTO clienteDTO) {

        return ClienteEntity
                .builder()
                .id(clienteDTO.getId())
                .build();
        /*
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(clienteDTO.getId());
        clienteEntity.setTipoIdentificacion(clienteDTO.getTipoIdentificacion());
        clienteEntity.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
        clienteEntity.setNombre(clienteDTO.getNombre());
        clienteEntity.setApellido(clienteDTO.getApellido());
        clienteEntity.setEmail(clienteDTO.getEmail());
        clienteEntity.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        clienteEntity.setFechaCreacion(clienteDTO.getFechaCreacion());
        clienteEntity.setFechaModificacion(clienteDTO.getFechaModificacion());

        return clienteEntity;

         */
    }
}
