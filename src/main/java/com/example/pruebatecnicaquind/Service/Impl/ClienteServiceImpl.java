package com.example.pruebatecnicaquind.Service.Impl;

import com.example.pruebatecnicaquind.Constans.MessageAplication;
import com.example.pruebatecnicaquind.Dto.ClienteDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Mapper.ClienteMapper;
import com.example.pruebatecnicaquind.Repository.ClienteRepository;
import com.example.pruebatecnicaquind.Service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

/***
 * Service containing the client's service logic
 */

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Object createCliente(ClienteDto clienteDTO) {
        Boolean edadValida = validateEdadCliente(clienteDTO.getFechaNacimiento());
        if (!edadValida){
            return MessageAplication.CANNOTMINOR;
        }

        clienteDTO.setFechaCreacion(LocalDateTime.now());
        clienteDTO.setFechaModificacion(null);
        ClienteEntity saveInformation = ClienteMapper.dtoToClienteEntity(clienteDTO);
        return clienteRepository.save(saveInformation);
    }

    @Override
    public Object updateCliente(String numeroIdentificacion, ClienteDto clienteDTO) {
        Optional<ClienteEntity> existingClienteEntity = clienteRepository.findClienteEntityByNumeroIdentificacion(numeroIdentificacion);

        Boolean edadValida = validateEdadCliente(clienteDTO.getFechaNacimiento());
        if (!edadValida){
            return MessageAplication.CANNOTMINOR;
        }

        if (existingClienteEntity.isPresent()){
            existingClienteEntity.get().setTipoIdentificacion(clienteDTO.getTipoIdentificacion());
            existingClienteEntity.get().setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
            existingClienteEntity.get().setNombre(clienteDTO.getNombre());
            existingClienteEntity.get().setApellido(clienteDTO.getApellido());
            existingClienteEntity.get().setEmail(clienteDTO.getEmail());
            existingClienteEntity.get().setFechaNacimiento(clienteDTO.getFechaNacimiento());
            existingClienteEntity.get().setFechaModificacion(LocalDateTime.now());
            return clienteRepository.save(existingClienteEntity.get());
        }

        return null;
    }

    @Override
    public String deleteCliente(String numeroIdentificacion) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findClienteEntityByNumeroIdentificacion(numeroIdentificacion);
        if (clienteEntity.isPresent()){

            if (clienteEntity.get().getProductoEntity().isEmpty()) {
                clienteRepository.deleteById(clienteEntity.get().getId());
                return MessageAplication.DELETECLIENT;
            }
            return MessageAplication.DELETECLIENTERROR;
        }

        return MessageAplication.CLIENTNOTFOUND;
    }

    private Boolean validateEdadCliente(String fechaNacimiento) {
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(fechaNac, ahora);
            int edad = periodo.getYears();
            if (edad < 18) {
                return false;
            }
            return true;
        }

}
