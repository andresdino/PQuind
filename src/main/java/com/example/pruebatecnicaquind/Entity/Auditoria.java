package com.example.pruebatecnicaquind.Entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class Auditoria {
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;
}
