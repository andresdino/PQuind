package com.example.pruebatecnicaquind.Entity;

import com.example.pruebatecnicaquind.Enums.EstadoCuenta;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "cuenta_corriente")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaCorrienteEntity extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipoCuenta;

    @Column(unique = true, nullable = false)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;

    @Column(nullable = false)
    private BigDecimal saldo;

    private boolean exentaGMF;
}
