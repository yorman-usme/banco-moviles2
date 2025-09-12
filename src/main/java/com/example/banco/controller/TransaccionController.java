package com.example.banco.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.banco.modelo.Transaccion;
import com.example.banco.service.TransaccionService;

@RestController
@RequestMapping("/api/v1/transacciones")
@CrossOrigin(origins = "*")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    public List<Transaccion> obtenerTodas() {
        return transaccionService.obtenerTransacciones();
    }

    @PostMapping
    public Transaccion crearTransaccion(@RequestBody Transaccion nuevaTransaccion) {
        return transaccionService.crearTransaccion(nuevaTransaccion);
    }
}
