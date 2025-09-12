package com.example.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banco.modelo.Cuenta;
import com.example.banco.service.CuentaService;


@RestController
@RequestMapping("/api/v1/categoria") // Mapea todas las peticiones a esta URL base
@CrossOrigin(origins = "*")
public class CuentaController {
    
        @Autowired
    private CuentaService cuentaServise;

    @GetMapping
    public List<Cuenta> obtenerTodos(){
        return cuentaServise.obtenerCuentas();
    }

    @PostMapping
 public Cuenta crearProducto(@RequestBody Cuenta nuevCuenta) {
    return cuentaServise.crearCuenta(nuevCuenta);
    }

}
