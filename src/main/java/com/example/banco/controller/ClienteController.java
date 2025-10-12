package com.example.banco.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.ClienteCreateDTO;
import com.example.banco.service.ClienteService;
import com.example.banco.auth.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            ClienteDTO cliente = clienteService.autenticar(
                loginRequest.getCorreoElectronico(), 
                loginRequest.getPassword()
            );
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Credenciales inválidas");
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Conexión exitosa");
    }
}
