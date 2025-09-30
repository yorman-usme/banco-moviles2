package com.example.banco.mapper;

import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.CuentaCreateDTO;
import com.example.banco.modelo.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);

    // De entidad a DTO
    @Mapping(source = "cliente.id", target = "clienteId")
    CuentaDTO toDTO(Cuenta cuenta);

    // De CreateDTO a entidad
    @Mapping(source = "clienteId", target = "cliente.id")
    Cuenta toEntity(CuentaCreateDTO cuentaCreateDTO);
}
