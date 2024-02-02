package com.example.pruebatecnicaquind.Dto;

import com.example.pruebatecnicaquind.Entity.Auditoria;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO extends Auditoria implements Serializable {
    Long id;
    String tipoIdentificacion;
    Long numeroIdentificacion;
    @Length(message = "El nombre debe tener al menos 2 caracteres", min = 2)
    String nombre;
    @Length(message = "El apellido debe tener al menos 2 caracteres", min = 2)
    String apellido;
    String email;
    String fechaNacimiento;
    private ProductoEntity productos;


    public ClienteDTO(Long id, String tipoIdentificacion, Long numeroIdentificacion,
                              String nombre, String apellido, String email, String fechaNacimiento
                              ) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }


}