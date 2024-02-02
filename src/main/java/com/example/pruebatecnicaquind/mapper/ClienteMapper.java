package com.example.pruebatecnicaquind.mapper;

import com.example.pruebatecnicaquind.Dto.ClienteDTO;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;

public class ClienteMapper {

    public static ClienteEntity dtoToClienteEntity(ClienteDTO clienteDTO) {

        return ClienteEntity
                .builder()
                .id(clienteDTO.getId())
                .nombre(clienteDTO.getNombre())
                .tipoIdentificacion(clienteDTO.getTipoIdentificacion())
                .numeroIdentificacion(clienteDTO.getNumeroIdentificacion())
                .apellido(clienteDTO.getApellido())
                .email(clienteDTO.getEmail())
                .fechaNacimiento(clienteDTO.getFechaNacimiento())
                .build();
    }


    /*
    public static ClienteDTO entityToClienteDto(ClienteEntity clienteEntity) {
        return new ClienteDTO(
                clienteEntity.getId(),
                clienteEntity.getTipoIdentificacion(),
                clienteEntity.getNumeroIdentificacion(),
                clienteEntity.getNombre(),
                clienteEntity.getApellido(),
                clienteEntity.getEmail(),
                clienteEntity.getFechaNacimiento() != null ? clienteEntity.getFechaNacimiento() : null,
                clienteEntity.getFechaCreacion(),
                clienteEntity.getFechaModificacion()
        );
    }

     */
}
