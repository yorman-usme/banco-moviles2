package com.example.banco.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.ClienteCreateDTO;
import com.example.banco.mapper.ClienteMapper;
import com.example.banco.modelo.Cliente;
import com.example.banco.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public ClienteDTO buscarPorIdentificacion(String identificacion) {
        Cliente cliente = clienteRepository.findByIdentificacion(identificacion);
        return clienteMapper.toDTO(cliente);
    }

    @Override
    public List<ClienteDTO> obtenerTodas() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO crearCliente(ClienteCreateDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);

        // 🔹 Validaciones (igual que antes)
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

        Cliente guardado = clienteRepository.save(cliente);
        return clienteMapper.toDTO(guardado);
    }
}
