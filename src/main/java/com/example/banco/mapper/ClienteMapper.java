package com.example.banco.mapper;

import org.mapstruct.Mapper;
import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.ClienteCreateDTO;
import com.example.banco.modelo.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteCreateDTO clienteCreateDTO);
}
