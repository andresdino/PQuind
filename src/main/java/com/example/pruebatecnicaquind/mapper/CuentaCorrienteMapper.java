package com.example.pruebatecnicaquind.mapper;

import com.example.pruebatecnicaquind.Dto.CuentaCorrienteDto;
import com.example.pruebatecnicaquind.Entity.CuentaCorrienteEntity;

public class CuentaCorrienteMapper {

    public static CuentaCorrienteEntity dtoToCuentaCorrienteEntity(CuentaCorrienteDto cuentaCorrienteDto){
        return CuentaCorrienteEntity
                .builder()

                .build();
    }
}
