package com.example.pruebatecnicaquind.Repository;

import com.example.pruebatecnicaquind.Entity.Cuentas.CuentaCorriente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaCorrienteRepository extends CrudRepository<CuentaCorriente,Long> {
}
