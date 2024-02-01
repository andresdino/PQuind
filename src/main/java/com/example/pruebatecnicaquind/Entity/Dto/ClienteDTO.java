package com.example.pruebatecnicaquind.Entity.Dto;

import com.example.pruebatecnicaquind.Entity.Cliente;
import com.example.pruebatecnicaquind.Entity.Producto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ClienteDTO implements Serializable {
    Long id;
    String tipoIdentificacion;
    Long numeroIdentificacion;
    @Length(message = "El nombre debe tener al menos 2 caracteres", min = 2)
    String nombre;
    @Length(message = "El apellido debe tener al menos 2 caracteres", min = 2)
    String apellido;
    String email;
    String fechaNacimiento;
    LocalDateTime fechaCreacion;
    LocalDateTime fechaModificacion;
    private Producto productos;


    public ClienteDTO(Long id, String tipoIdentificacion, Long numeroIdentificacion,
                              String nombre, String apellido, String email, String fechaNacimiento,
                              LocalDateTime fechaCreacion, LocalDateTime fechaModificacion) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public static ClienteDTO fromCliente(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getTipoIdentificacion(),
                cliente.getNumeroIdentificacion(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEmail(),
                cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento() : null,
                cliente.getFechaCreacion(),
                cliente.getFechaModificacion()
        );
    }


}