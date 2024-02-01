package com.example.pruebatecnicaquind.Entity;

import com.example.pruebatecnicaquind.Constants.MessageAplication;
import com.example.pruebatecnicaquind.Enums.EstadoCuenta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "cuenta_ahorro_id")
@DiscriminatorValue("CUENTA_AHORRO")
public class CuentaAhorroEntity extends ProductoEntity {

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

//    @Override
//    public void setSaldo(BigDecimal saldo) {
//        if (saldo.compareTo(BigDecimal.ZERO) < 0) {
//            throw new IllegalArgumentException(MessageAplication.BALANCECANNOTLESS0);
//        }
//
//        super.setSaldo(saldo);
//    }


}