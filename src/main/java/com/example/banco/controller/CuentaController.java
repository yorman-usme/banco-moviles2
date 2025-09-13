package com.example.banco.controller;

import com.example.banco.modelo.Cuenta;
import com.example.banco.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cuentas")
@CrossOrigin(origins = "*")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> obtenerTodas() {
        return cuentaService.obtenerCuentas();
    }

    @GetMapping("/{id}")
    public Cuenta obtenerPorId(@PathVariable Long id) {
        return cuentaService.obtenerCuentaPorId(id);
    }

    @PostMapping
    public Cuenta crearCuenta(@RequestBody Cuenta nuevaCuenta) {
        return cuentaService.crearCuenta(nuevaCuenta);
    }

    @PutMapping("/{id}")
    public Cuenta actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaActualizada) {
        return cuentaService.actualizarCuenta(id, cuentaActualizada);
    }

    @DeleteMapping("/{id}")
    public String eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return "Cuenta eliminada con éxito";
    }
}
