package com.example.banco.service;

import com.example.banco.modelo.Transaccion;
import java.util.List;

public interface TransaccionService {
    List<Transaccion> obtenerTransacciones();
    Transaccion obtenerTransaccionPorId(Long id);
    Transaccion crearTransaccion(Transaccion transaccion);
    Transaccion actualizarTransaccion(Long id, Transaccion transaccion);
    void eliminarTransaccion(Long id);
    Transaccion transferirFondos(Long cuentaOrigenId, Long cuentaDestinoId, Double monto);
    List<Transaccion> obtenerTransaccionesPorCuenta(Long cuentaId);
    String depositar(String numeroCuenta, Double monto);
}
