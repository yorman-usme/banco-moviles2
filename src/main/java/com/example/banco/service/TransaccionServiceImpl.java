package com.example.banco.service;

import com.example.banco.modelo.Cuenta;
import com.example.banco.modelo.Transaccion;
import com.example.banco.repository.CuentaRepository;
import com.example.banco.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


        // Registrar transacción
        Transaccion transaccion = new Transaccion();
        transaccion.setCuenta(cuentaOrigen);
        transaccion.setMonto(monto);
        transaccion.setTipo("TRANSFERENCIA");
        transaccion.setCuentaOrigenNumero(cuentaOrigen.getNumeroCuenta());
        transaccion.setCuentaDestinoNumero(cuentaDestino.getNumeroCuenta());
        transaccionRepository.save(transaccion);

        return transaccion;
    }

    @Override
    public String depositar(String numeroCuenta, Double monto) {
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
        transaccion.setTipo("Depósito");
        transaccionRepository.save(transaccion);

        return "Depósito exitoso. Nuevo saldo: " + cuenta.getSaldo();
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
