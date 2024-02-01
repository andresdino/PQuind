package com.example.pruebatecnicaquind.Service;

import com.example.pruebatecnicaquind.Entity.Cliente;
import com.example.pruebatecnicaquind.Repository.ClienteRepository;
import com.example.pruebatecnicaquind.Service.Implementation.IClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Cliente createCliente(Cliente cliente) {
        validarEdadCliente(cliente.getFechaNacimiento());
        cliente.setFechaCreacion(LocalDateTime.now());
        cliente.setFechaModificacion(LocalDateTime.now());
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));

        validarEdadCliente(cliente.getFechaNacimiento());

        existingCliente.setFechaModificacion(LocalDateTime.now());
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));

        if (!cliente.getProductos().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar el cliente porque tiene productos vinculados.");
        }

        clienteRepository.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getAllClientes() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    @Transactional
    public Cliente getClienteById(Long id) {
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
