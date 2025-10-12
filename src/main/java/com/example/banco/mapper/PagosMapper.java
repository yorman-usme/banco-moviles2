package com.example.banco.mapper;

import com.example.banco.dto.PagosDTO;
import com.example.banco.dto.PagosCreateDTO;
import com.example.banco.modelo.Pagos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PagosMapper {

    PagosMapper INSTANCE = Mappers.getMapper(PagosMapper.class);

    // De entidad a DTO
    @Mapping(source = "cliente.id", target = "clienteId")
    PagosDTO toDTO(Pagos pago);

    // De CreateDTO a entidad
    @Mapping(source = "clienteId", target = "cliente.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaPago", ignore = true)
    Pagos toEntity(PagosCreateDTO pagoCreateDTO);
}
