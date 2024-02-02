package com.example.pruebatecnicaquind.Repository;


import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

    Optional<ProductoEntity> findProductoEntityByNumeroCuenta(String numeroCuenta);
    Boolean findByNumeroCuenta(String numero);
    Boolean existsByNumeroCuenta(String numero);

}
