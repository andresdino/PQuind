package com.example.pruebatecnicaquind.Dto;

import com.example.pruebatecnicaquind.Entity.Auditoria;
import com.example.pruebatecnicaquind.Enum.EstadoCuenta;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarEstadoCuentaDto extends Auditoria {

    private String numeroCuenta;
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;
}
