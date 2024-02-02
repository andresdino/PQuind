package com.example.pruebatecnicaquind.Entity;

import com.example.pruebatecnicaquind.Enums.EstadoCuenta;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class ProductoEntity {

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

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity clienteEntity;

    private boolean activa;


}
