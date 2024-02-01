package com.example.pruebatecnicaquind.Entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Auditoria {

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;
}
