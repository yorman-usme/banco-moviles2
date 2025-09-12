package com.example.banco.service;

import java.util.List;

import com.example.banco.modelo.Cuenta;


public interface CuentaService {
    List<Cuenta> obtenerCuentas();
    Cuenta crearCuenta(Cuenta cuenta);
    
}
