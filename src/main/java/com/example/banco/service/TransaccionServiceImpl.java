package com.example.banco.service;

import com.example.banco.modelo.Transaccion;
import com.example.banco.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Override
    public List<Transaccion> obtenerTransacciones() {
        return transaccionRepository.findAll();
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
