package com.example.pruebatecnicaquind.Repository;

import com.example.pruebatecnicaquind.Entity.CuentaAhorroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaAhorroRepository extends JpaRepository<CuentaAhorroEntity, Long> {


    Optional<CuentaAhorroEntity> findCuentaAhorroEntityByNumeroCuenta(String numeroCuenta);
}
