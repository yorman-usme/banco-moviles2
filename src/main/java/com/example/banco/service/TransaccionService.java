package com.example.banco.service;

import java.util.List;

import com.example.banco.modelo.Transaccion;

public interface TransaccionService {

    List<Transaccion> obtenerTodas();

    Transaccion crearTransaccion(Transaccion transaccion);
}
