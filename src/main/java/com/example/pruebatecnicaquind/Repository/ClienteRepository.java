package com.example.pruebatecnicaquind.Repository;

import com.example.pruebatecnicaquind.Entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
