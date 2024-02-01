package com.example.pruebatecnicaquind.Entity;

import com.example.pruebatecnicaquind.Entity.Cuentas.EstadoCuenta;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Producto {

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
    private Cliente cliente;

    private boolean activa;

    ////////////////////////  VERIFICAR   ///////////////////////////////

    public abstract void realizarTransaccion(BigDecimal monto);

    public void consignar(BigDecimal monto) {
        // Lógica genérica de consignación para productos financieros
        this.setSaldo(this.getSaldo().add(monto));
        this.setFechaModificacion(LocalDateTime.now());
    }

    public void retirar(BigDecimal monto) {
        // Lógica genérica de retiro para productos financieros
        if (monto.compareTo(BigDecimal.ZERO) > 0 && this.getSaldo().compareTo(monto) >= 0) {
            this.setSaldo(this.getSaldo().subtract(monto));
            this.setFechaModificacion(LocalDateTime.now());
        } else {
            throw new IllegalStateException("Monto inválido para retiro.");
        }
    }

    public void transferir(Producto destino, BigDecimal monto) {
        // Lógica genérica de transferencia para productos financieros
        if (monto.compareTo(BigDecimal.ZERO) > 0 && this.getSaldo().compareTo(monto) >= 0) {
            this.retirar(monto);
            destino.consignar(monto);
        } else {
            throw new IllegalStateException("Monto inválido para transferencia.");
        }
    }

    public void cancelarCuenta() {
        if (saldo.equals(BigDecimal.ZERO)) {
            this.estado = EstadoCuenta.CANCELADA;
        } else {
            throw new IllegalStateException("No se puede cancelar una cuenta con un saldo diferente de $0.");
        }
    }




}

