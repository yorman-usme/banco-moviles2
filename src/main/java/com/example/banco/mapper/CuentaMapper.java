package com.example.banco.mapper;

import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.CuentaCreateDTO;
import com.example.banco.modelo.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
 @Mapping(source = "cliente.id", target = "clienteId")
  CuentaDTO toDTO(Cuenta cuenta);


    @Mapping(source = "clienteId", target = "cliente.id") 
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "saldo", ignore = true) // Saldo se maneja en el servicio 
    @Mapping(target = "transacciones", ignore = true) // Ignorar colección
Cuenta toEntity(CuentaCreateDTO cuentaCreateDTO);
}
