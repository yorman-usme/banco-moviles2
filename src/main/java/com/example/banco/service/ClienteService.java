package com.example.banco.service;

import java.util.List;
import com.example.banco.modelo.Cliente;

public interface ClienteService {
    List<Cliente> obtenerTodas();
    Cliente crearCliente(Cliente cliente);
    Cliente buscarPorIdentificacion(String identificacion);
}