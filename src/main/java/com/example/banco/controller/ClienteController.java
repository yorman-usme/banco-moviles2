package com.example.banco.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.ClienteCreateDTO;
import com.example.banco.service.ClienteService;

@RestController
@RequestMapping("/api/v1/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/buscar")
    public ClienteDTO buscarPorIdentificacion(@RequestParam String identificacion) {
        return clienteService.buscarPorIdentificacion(identificacion);
    }

    @GetMapping
    public List<ClienteDTO> obtenerTodos() {
        return clienteService.obtenerTodas();
    }

    @PostMapping
    public ClienteDTO crearCliente(@RequestBody ClienteCreateDTO nuevoCliente) {
        return clienteService.crearCliente(nuevoCliente);
    }
}
