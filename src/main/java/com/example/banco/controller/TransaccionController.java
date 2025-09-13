package com.example.banco.controller;

import com.example.banco.modelo.Transaccion;
import com.example.banco.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public Transaccion obtenerPorId(@PathVariable Long id) {
        return transaccionService.obtenerTransaccionPorId(id);
    }

    @PostMapping
    public Transaccion crearTransaccion(@RequestBody Transaccion nuevaTransaccion) {
        return transaccionService.crearTransaccion(nuevaTransaccion);
    }

    @PutMapping("/{id}")
    public Transaccion actualizarTransaccion(@PathVariable Long id, @RequestBody Transaccion transaccionActualizada) {
        return transaccionService.actualizarTransaccion(id, transaccionActualizada);
    }

    @DeleteMapping("/{id}")
    public String eliminarTransaccion(@PathVariable Long id) {
        transaccionService.eliminarTransaccion(id);
        return "Transacción eliminada con éxito";
    }
}
