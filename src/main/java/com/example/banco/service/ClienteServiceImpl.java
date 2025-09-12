package com.example.banco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banco.modelo.Cliente;
import com.example.banco.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente buscarPorIdentificacion(String identificacion) {
        return clienteRepository.findByIdentificacion(identificacion);
    }

    @Override
    public List<Cliente> obtenerTodas() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {

        if (cliente.getIdentificacion() == null || cliente.getIdentificacion().isBlank()) {
            throw new RuntimeException("La identificación no puede estar vacía.");
        }
        if (!cliente.getIdentificacion().matches("\\d+")) {
            throw new RuntimeException("La identificación debe contener solo números.");
        }
        if (clienteRepository.findByIdentificacion(cliente.getIdentificacion()) != null) {
            throw new RuntimeException("Ya existe un cliente con esta identificación.");
        }

        if (cliente.getName() == null || cliente.getName().isBlank()) {
            throw new RuntimeException("El nombre es obligatorio.");
        }
        if (!cliente.getName().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            throw new RuntimeException("El nombre solo puede contener letras.");
        }

        if (cliente.getApellido() == null || cliente.getApellido().isBlank()) {
            throw new RuntimeException("El apellido es obligatorio.");
        }

        if (cliente.getCorreo_electronico() != null &&
            !cliente.getCorreo_electronico().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("El correo electrónico no es válido.");
        }

        if (cliente.getTelefono() != null &&
            !cliente.getTelefono().matches("\\d{7,10}")) {
            throw new RuntimeException("El teléfono debe contener entre 7 y 10 dígitos.");
        }

        return clienteRepository.save(cliente);
    }
}