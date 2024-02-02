package com.example.pruebatecnicaquind.Entity;

import com.example.pruebatecnicaquind.Constants.MessageAplication;
import com.example.pruebatecnicaquind.Enums.EstadoCuenta;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cuenta_ahorro")
public class CuentaAhorroEntity  extends Auditoria{

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