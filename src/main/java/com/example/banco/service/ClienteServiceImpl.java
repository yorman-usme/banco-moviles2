package com.example.banco.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.ClienteCreateDTO;
import com.example.banco.mapper.ClienteMapper;
import com.example.banco.modelo.Cliente;
import com.example.banco.modelo.Cuenta;
import com.example.banco.repository.ClienteRepository;
import com.example.banco.repository.CuentaRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

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

        // 🔎 Validaciones
        if (cliente.getIdentificacion() == null || cliente.getIdentificacion().isBlank()) {
            throw new RuntimeException("La identificación no puede estar vacía.");
        }
        if (!cliente.getIdentificacion().matches("\\d+")) {
            throw new RuntimeException("La identificación debe contener solo números.");
        }
        if (clienteRepository.findByIdentificacion(cliente.getIdentificacion()) != null) {
            throw new RuntimeException("Ya existe un cliente con esta identificación.");
        }

        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            throw new RuntimeException("El nombre es obligatorio.");
        }
        if (!cliente.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            throw new RuntimeException("El nombre solo puede contener letras.");
        }

        if (cliente.getPassword() == null || cliente.getPassword().isBlank()) {
            throw new RuntimeException("La contraseña es obligatoria.");
        }

        if (cliente.getCorreoElectronico() == null || cliente.getCorreoElectronico().isBlank()) {
            throw new RuntimeException("El correo electrónico es obligatorio.");
        }
        if (!cliente.getCorreoElectronico().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("El correo electrónico no es válido.");
        }

        if (cliente.getTelefono() != null && !cliente.getTelefono().matches("\\d{7,10}")) {
            throw new RuntimeException("El teléfono debe contener entre 7 y 10 dígitos.");
        }

        // 💾 Guardar cliente primero
        Cliente guardado = clienteRepository.save(cliente);

        // 💳 Crear cuenta automáticamente
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(guardado);
        cuenta.setNumeroCuenta(generarNumeroCuenta());
        cuenta.setSaldo(0.0); // saldo inicial
        cuentaRepository.save(cuenta);

        return clienteMapper.toDTO(guardado);
    }

    private String generarNumeroCuenta() {
        String numero;
        do {
            numero = String.valueOf((long) (Math.random() * 9000000000L) + 1000000000L);
        } while (cuentaRepository.findByNumeroCuenta(numero).isPresent());
        return numero;
    }

    @Override
    public ClienteDTO autenticar(String correoElectronico, String password) {
        Cliente cliente = clienteRepository.findByCorreoElectronico(correoElectronico)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!cliente.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return clienteMapper.toDTO(cliente);
    }
}
