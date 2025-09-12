package com.example.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.banco.modelo.Cliente;
import com.example.banco.service.ClienteService;

@RestController
@RequestMapping("/api/v1/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/buscar")
    public Cliente buscarPorIdentificacion(@RequestParam String identificacion) {
        return clienteService.buscarPorIdentificacion(identificacion);
    }

    @GetMapping
    public List<Cliente> obtenerTodos() {
        return clienteService.obtenerTodas();
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente nuevoCliente) {
        return clienteService.crearCliente(nuevoCliente);
    }
}
