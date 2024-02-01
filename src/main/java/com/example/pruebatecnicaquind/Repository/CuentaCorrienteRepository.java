package com.example.pruebatecnicaquind.Repository;

import com.example.pruebatecnicaquind.Entity.CuentaCorrienteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaCorrienteRepository extends CrudRepository<CuentaCorrienteEntity,Long> {
    Optional<CuentaCorrienteEntity> findCuentaCorrienteEntityByNumeroCuenta(String numeroCuenta);
}
