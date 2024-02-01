package com.example.pruebatecnicaquind.Repository;

import com.example.pruebatecnicaquind.Entity.Cuentas.CuentaAhorro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaAhorroRepository extends JpaRepository<CuentaAhorro, Long> {

}
