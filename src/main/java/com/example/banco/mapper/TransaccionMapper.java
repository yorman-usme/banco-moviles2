package com.example.banco.mapper;

import com.example.banco.dto.TransaccionDTO;
import com.example.banco.dto.TransaccionCreateDTO;
import com.example.banco.modelo.Transaccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface TransaccionMapper {

    @Mapping(source = "cuenta.id", target = "cuentaId")
    TransaccionDTO toDTO(Transaccion transaccion);



    // De CreateDTO a entidad
    @Mapping(source = "cuentaId", target = "cuenta.id")
    @Mapping(target = "id", ignore = true)
    Transaccion toEntity(TransaccionCreateDTO transaccionCreateDTO);
}
