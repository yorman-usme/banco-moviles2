package com.example.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.banco.modelo.Cliente;
import com.example.banco.service.ClienteService;

@RestController // Indica que esta clase es un controlador que manejará peticiones REST
@RequestMapping("/api/v1/cliente") // Mapea todas las peticiones a esta URL base
@CrossOrigin(origins = "*")
public class ClienteController {

    @GetMapping("/buscar")
    public Cliente buscarPorNombre(String nombre) {
        return clienteService.buscarPorNombre(nombre);
    }

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> obtenerTodos() {
        return clienteService.obtenerTodas();
    }

    @PostMapping
    public Cliente crearProducto(@RequestBody Cliente nuevCliente) {
        return clienteService.crearCliente(nuevCliente);
    }
}
