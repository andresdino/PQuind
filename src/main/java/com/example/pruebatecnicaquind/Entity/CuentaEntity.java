package com.example.pruebatecnicaquind.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cuenta")
@Getter
@Setter
public class CuentaEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_cuenta")
    private String tipoCuenta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity productoEntity;


}
