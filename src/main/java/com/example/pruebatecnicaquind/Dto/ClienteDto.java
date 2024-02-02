package com.example.pruebatecnicaquind.Dto;

import com.example.pruebatecnicaquind.Entity.Auditoria;
import com.example.pruebatecnicaquind.Enum.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Builder
@Getter
@Setter
public class ClienteDto extends Auditoria {
    private TipoDocumento tipoIdentificacion;
    private  String numeroIdentificacion;
    @Length(message = "El nombre debe tener al menos 2 caracteres", min = 2)
    private  String nombre;
    @Length(message = "El apellido debe tener al menos 2 caracteres", min = 2)
    private  String apellido;
    private  String email;
    private  String fechaNacimiento;
    @JsonIgnore
    private List<ProductoDto> productoDto;
}
