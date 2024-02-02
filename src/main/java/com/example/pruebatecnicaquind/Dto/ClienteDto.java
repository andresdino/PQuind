package com.example.pruebatecnicaquind.Dto;

import com.example.pruebatecnicaquind.Entity.Auditoria;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Builder
@Getter
public class ClienteDto extends Auditoria {
    private  String tipoIdentificacion;
    private  Long numeroIdentificacion;
    @Length(message = "El nombre debe tener al menos 2 caracteres", min = 2)
    private  String nombre;
    @Length(message = "El apellido debe tener al menos 2 caracteres", min = 2)
    private  String apellido;
    private  String email;
    private  String fechaNacimiento;
    private List<ProductoDto> productoDto;
}
