package com.example.pruebatecnicaquind.Service;

import com.example.pruebatecnicaquind.Constants.MessageAplication;
import com.example.pruebatecnicaquind.Dto.ClienteDTO;
import com.example.pruebatecnicaquind.Entity.ClienteEntity;
import com.example.pruebatecnicaquind.Repository.ClienteRepository;
import com.example.pruebatecnicaquind.Service.Implementation.IClienteService;
import com.example.pruebatecnicaquind.mapper.ClienteMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
        public ClienteEntity createCliente(ClienteDTO clienteDTO) {

        validarEdadCliente(clienteDTO.getFechaNacimiento());
        clienteDTO.setFechaCreacion(LocalDateTime.now());
        ClienteEntity saveInformation = ClienteMapper.dtoToClienteEntity(clienteDTO);
        return clienteRepository.save(saveInformation);
    }

    @Override
    @Transactional
    public ClienteEntity updateCliente(Long id, ClienteDTO clienteDTO) {
        ClienteEntity existingClienteEntity = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));

        validarEdadCliente(clienteDTO.getFechaNacimiento());

        existingClienteEntity.setFechaModificacion(LocalDateTime.now());
        ClienteEntity updateInformation = ClienteMapper.dtoToClienteEntity(clienteDTO);
        return clienteRepository.save(updateInformation);
    }

    @Override
    @Transactional
    public void deleteCliente(Long id) {

        ClienteEntity clienteEntity = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));

        if (!clienteEntity.getProductoEntities().isEmpty()) {
            throw new IllegalStateException(MessageAplication.DELETECLIENTERROR);
        }

        clienteRepository.delete(clienteEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteEntity> getAllClientes() {
        return  clienteRepository.findAll();
    }

    @Override
    @Transactional
    public ClienteEntity getClienteById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException( "No such client for id " + id));
    }

    private void validarEdadCliente(String fechaNacimiento) {
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
