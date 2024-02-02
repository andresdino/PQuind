package com.example.pruebatecnicaquind.Mapper;

import com.example.pruebatecnicaquind.Dto.RequestCuentaClienteDto;
import com.example.pruebatecnicaquind.Entity.CuentaEntity;
import com.example.pruebatecnicaquind.Entity.ProductoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CuentaMapper {

    public static CuentaEntity dtoToCuentaEntity(RequestCuentaClienteDto requestCuentaClienteDto){
        ProductoEntity productoEntity = ProductoMapper.dtoToProductoEntity(requestCuentaClienteDto.getProductoDto());

        CuentaEntity cuentaEntity = new CuentaEntity();
        cuentaEntity.setProductoEntity(productoEntity);
        cuentaEntity.setTipoCuenta(requestCuentaClienteDto.getTipoCuenta());

     return cuentaEntity;
    }

    public static List<CuentaEntity> listDtoToListEntity(List<RequestCuentaClienteDto> cuentasDto) {
        return cuentasDto.stream()
                .map(CuentaMapper::dtoToCuentaEntity)
                .collect(Collectors.toList());
    }

}
