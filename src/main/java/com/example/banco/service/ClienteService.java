package com.example.banco.service;

import java.util.List;
import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.ClienteCreateDTO;

public interface ClienteService {
    List<ClienteDTO> obtenerTodas();
    ClienteDTO crearCliente(ClienteCreateDTO clienteDTO);
    ClienteDTO buscarPorIdentificacion(String identificacion);
    ClienteDTO autenticar(String correoElectronico, String password);
}