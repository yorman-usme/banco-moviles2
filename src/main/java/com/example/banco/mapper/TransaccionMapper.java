package com.example.banco.mapper;

import com.example.banco.dto.TransaccionDTO;
import com.example.banco.dto.TransaccionCreateDTO;
import com.example.banco.modelo.Transaccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {

    TransaccionMapper INSTANCE = Mappers.getMapper(TransaccionMapper.class);

    // De entidad a DTO
    @Mapping(source = "cuenta.id", target = "cuentaId")
    TransaccionDTO toDTO(Transaccion transaccion);

    // De CreateDTO a entidad
    @Mapping(source = "cuentaId", target = "cuenta.id")
    Transaccion toEntity(TransaccionCreateDTO transaccionCreateDTO);
}
