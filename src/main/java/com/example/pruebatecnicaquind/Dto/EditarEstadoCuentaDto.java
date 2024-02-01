package com.example.pruebatecnicaquind.Dto;

import com.example.pruebatecnicaquind.Entity.Auditoria;
import com.example.pruebatecnicaquind.Enums.EstadoCuenta;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public class EditarEstadoCuentaDto extends Auditoria {
    private String numeroCuenta;
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;
}
