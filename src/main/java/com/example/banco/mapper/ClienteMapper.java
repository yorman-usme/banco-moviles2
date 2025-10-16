package com.example.banco.mapper;

import org.mapstruct.Mapper;
import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.ClienteCreateDTO;
import com.example.banco.modelo.Cliente;
import org.mapstruct.Mapping; 
import org.mapstruct.Mappings; 

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    
    ClienteDTO toDTO(Cliente cliente);

    @Mappings({ 
         @Mapping(target = "id", ignore = true),
        @Mapping(target = "cuentas", ignore = true),
    }) 
    Cliente toEntity(ClienteCreateDTO clienteCreateDTO);
}