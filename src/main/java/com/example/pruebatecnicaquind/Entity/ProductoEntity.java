package com.example.pruebatecnicaquind.Entity;

import com.example.pruebatecnicaquind.Enum.EstadoCuenta;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity extends Auditoria{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "productoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CuentaEntity> cuenta;

    @Column(name = "numero_cuenta",unique = true, nullable = false)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_cuenta")
    private EstadoCuenta estado;

    @Column(name = "saldo_cuenta")
    private BigDecimal saldo;

    @Column(name = "exenta_gmf")
    private boolean exentaGMF;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity clienteEntity;


}
