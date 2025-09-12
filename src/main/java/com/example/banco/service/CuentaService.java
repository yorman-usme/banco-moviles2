package com.example.banco.service;

import com.example.banco.modelo.Cuenta;
import java.util.List;

public interface CuentaService {
    List<Cuenta> obtenerCuentas();
    Cuenta obtenerCuentaPorId(Long id);
    Cuenta crearCuenta(Cuenta cuenta);
    Cuenta actualizarCuenta(Long id, Cuenta cuenta);
    void eliminarCuenta(Long id);
}
