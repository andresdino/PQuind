package com.example.pruebatecnicaquind.Mapper;

import com.example.pruebatecnicaquind.Dto.ClienteDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;

public class ClienteMapper {
    public static ClienteEntity dtoToClienteEntity(ClienteDto clienteDTO) {

        return ClienteEntity
                .builder()
                .nombre(clienteDTO.getNombre())
                .tipoIdentificacion(clienteDTO.getTipoIdentificacion())
                .numeroIdentificacion(clienteDTO.getNumeroIdentificacion())
                .apellido(clienteDTO.getApellido())
                .email(clienteDTO.getEmail())
                .fechaNacimiento(clienteDTO.getFechaNacimiento())
                //.productoEntity(clienteDTO.getProductoDto())
                .build();
    }
}
