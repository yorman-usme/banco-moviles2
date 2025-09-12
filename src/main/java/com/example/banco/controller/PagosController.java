package com.example.banco.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.banco.modelo.Pagos;
import com.example.banco.service.PagosService;

@RestController
@RequestMapping("/api/v1/pagos")
@CrossOrigin(origins = "*")
public class PagosController {
    
    @Autowired
    private PagosService pagosService;

    @GetMapping
    public List<Pagos> obtenerTodos() {
        return pagosService.obtenerTodas();
    }

    @PostMapping
    public Pagos crearPago(@RequestBody Pagos nuevoPago) {
        return pagosService.crearPagos(nuevoPago);
    }
}
