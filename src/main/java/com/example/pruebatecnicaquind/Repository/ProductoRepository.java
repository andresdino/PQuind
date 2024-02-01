package com.example.pruebatecnicaquind.Repository;

import com.example.pruebatecnicaquind.Entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
}
