package com.example.pruebatecnicaquind.Service.Impl;

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
import java.util.List;

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
        validateEdadCliente(clienteDTO.getFechaNacimiento());
        clienteDTO.setFechaCreacion(LocalDateTime.now());
        ClienteEntity saveInformation = ClienteMapper.dtoToClienteEntity(clienteDTO);
        return clienteRepository.save(saveInformation);
    }

    @Override
    public Object updateCliente(Long id, ClienteDto clienteDTO) {
        return null;
    }

    @Override
    public void deleteCliente(Long id) {

    }

    private void validateEdadCliente(String fechaNacimiento) {
        if (fechaNacimiento != null) {
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(fechaNac, ahora);
            int edad = periodo.getYears();
            if (edad < 18) {
                throw new IllegalArgumentException("El cliente debe ser mayor de edad");
            }
        }
    }
}
