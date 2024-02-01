package com.example.pruebatecnicaquind.Repository;

import com.example.pruebatecnicaquind.Entity.ProductoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoEntity, Long> {
}
