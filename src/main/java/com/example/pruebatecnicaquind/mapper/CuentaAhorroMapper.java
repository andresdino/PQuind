package com.example.pruebatecnicaquind.mapper;

import com.example.pruebatecnicaquind.Dto.CuentaAhorroDto;
import com.example.pruebatecnicaquind.Entity.CuentaAhorroEntity;

public class CuentaAhorroMapper {

    public static CuentaAhorroEntity dtoToCuentaAhorroEntity(CuentaAhorroDto cuentaAhorroDto){
        return CuentaAhorroEntity
                .builder()

                .build();
    }
}
