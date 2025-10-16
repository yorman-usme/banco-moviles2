package com.example.banco.service;

import com.example.banco.modelo.Cuenta;
import com.example.banco.modelo.Transaccion;
import com.example.banco.repository.CuentaRepository;
import com.example.banco.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Transaccion transferirFondos(Long cuentaOrigenId, Long cuentaDestinoId, Double monto) {
        Cuenta cuentaOrigen = cuentaRepository.findById(cuentaOrigenId)
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada."));
        Cuenta cuentaDestino = cuentaRepository.findById(cuentaDestinoId)
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada."));

        if (cuentaOrigen.getSaldo() < monto) {
            throw new RuntimeException("Saldo insuficiente en la cuenta origen.");
        }

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);


        Transaccion salida = new Transaccion();
        salida.setCuenta(cuentaOrigen);
        salida.setMonto(monto);
        salida.setTipo("TRANSFERENCIA_SALIDA");
        salida.setCuentaOrigenNumero(cuentaOrigen.getNumeroCuenta());
        salida.setCuentaDestinoNumero(cuentaDestino.getNumeroCuenta());
        salida.setFecha(LocalDateTime.now());
        transaccionRepository.save(salida);

        // Registrar transacción de entrada
        Transaccion entrada = new Transaccion();
        entrada.setCuenta(cuentaDestino);
        entrada.setMonto(monto);
        entrada.setTipo("TRANSFERENCIA_ENTRADA");
        entrada.setCuentaOrigenNumero(cuentaOrigen.getNumeroCuenta());
        entrada.setCuentaDestinoNumero(cuentaDestino.getNumeroCuenta());
        entrada.setFecha(LocalDateTime.now());
        transaccionRepository.save(entrada);

        return salida;
    }

    @Override
    public Transaccion depositar(String numeroCuenta, Double monto) {
        if (monto <= 0) {
            throw new RuntimeException("El monto debe ser mayor que cero.");
        }

        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada."));

        cuenta.setSaldo(cuenta.getSaldo() + monto);
        cuentaRepository.save(cuenta);

        // Registrar transacción
        Transaccion transaccion = new Transaccion();
        transaccion.setCuenta(cuenta);
        transaccion.setMonto(monto);
        transaccion.setTipo("DEPOSITO");
        transaccion.setFecha(LocalDateTime.now());
        transaccionRepository.save(transaccion);

        return transaccion;
    }


@Override
public Transaccion retirar(String numeroCuenta, Double monto) {
    // 1. Buscar la cuenta
    Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada."));

    // 2. Validación de Saldo (CRÍTICO)
    if (cuenta.getSaldo() < monto) {
        throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro.");
    }

    // 3. Realizar la resta y guardar
    cuenta.setSaldo(cuenta.getSaldo() - monto);
    cuentaRepository.save(cuenta);

    // 4. Registrar transacción
    Transaccion transaccion = new Transaccion();
    transaccion.setCuenta(cuenta);
    transaccion.setMonto(monto);
    transaccion.setTipo("RETIRO"); // <-- Tipo correcto
    transaccion.setFecha(LocalDateTime.now());
    transaccionRepository.save(transaccion);

    return transaccion;
}


    @Override
    public List<Transaccion> obtenerTransacciones() {
        return transaccionRepository.findAll();
    }

        @Override
    public List<Transaccion> obtenerTransaccionesPorCuenta(Long cuentaId) {
        return transaccionRepository.findByCuentaId(cuentaId);
    }

    @Override
    public Transaccion obtenerTransaccionPorId(Long id) {
        return transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada con id: " + id));
    }

    @Override
    public Transaccion crearTransaccion(Transaccion transaccion) {
        if (transaccion.getCuenta() == null) {
            throw new RuntimeException("La transacción debe estar asociada a una cuenta.");
        }
        if (transaccion.getMonto() <= 0) {
            throw new RuntimeException("El monto debe ser mayor que cero.");
        }
        return transaccionRepository.save(transaccion);
    }

    @Override
    public Transaccion actualizarTransaccion(Long id, Transaccion transaccionActualizada) {
        Transaccion transaccion = obtenerTransaccionPorId(id);
        transaccion.setTipo(transaccionActualizada.getTipo());
        transaccion.setMonto(transaccionActualizada.getMonto());
        transaccion.setCuenta(transaccionActualizada.getCuenta());
        return transaccionRepository.save(transaccion);
    }

    @Override
    public void eliminarTransaccion(Long id) {
        if (!transaccionRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. La transacción no existe.");
        }
        transaccionRepository.deleteById(id);
    }
}
