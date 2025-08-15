package com.example.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banco.modelo.Pagos;
import com.example.banco.service.PagosService;




@RestController // Indica que esta clase es un controlador que manejará peticiones REST
@RequestMapping("/api/v1/pagos") // Mapea todas las peticiones a esta URL base
@CrossOrigin(origins = "*")
public class PagosController {
    
    @Autowired
    private PagosService pagosService;

    @GetMapping
    public List<Pagos> obtenerTodos(){
        return pagosService.obtenerTodas();
    }


    @PostMapping
 public Pagos crearProducto(@RequestBody Pagos nuevoPago) {
    return pagosService.crearPagos(nuevoPago);
    }


}
