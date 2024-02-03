package com.example.pruebatecnicaquind.Repository;

import com.example.pruebatecnicaquind.Entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {

}
