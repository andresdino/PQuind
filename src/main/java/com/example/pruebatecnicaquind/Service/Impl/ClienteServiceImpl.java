package com.example.pruebatecnicaquind.Service.Impl;

import com.example.pruebatecnicaquind.Dto.ClienteDto;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Mapper.ClienteMapper;
import com.example.pruebatecnicaquind.Repository.ClienteRepository;
import com.example.pruebatecnicaquind.Service.IClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteEntity> getAllClientes() {
        return null;
    }

    @Override
    public ClienteEntity getClienteById(Long id) {
        return null;
    }

    @Override
    public Object createCliente(ClienteDto clienteDTO) {
        Boolean edadValida = validateEdadCliente(clienteDTO.getFechaNacimiento());
        if (!edadValida){
            return "No puede ser menor bpbo hpt";
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
             return "No puede ser menor bpbo hpt";
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
        ClienteEntity clienteEntity = clienteRepository.findClienteEntityByNumeroIdentificacion(numeroIdentificacion)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + numeroIdentificacion));

        /*
        if (!clienteEntity.getProductoEntities().isEmpty()) {
            throw new IllegalStateException(MessageAplication.DELETECLIENTERROR);
        }

         */
        clienteRepository.delete(clienteEntity);
        return "Eliminado Exitosamente";
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
