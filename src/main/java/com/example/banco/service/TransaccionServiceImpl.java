package com.example.banco.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.banco.modelo.Transaccion;
import com.example.banco.repository.TransaccionRepository;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Override
    public List<Transaccion> obtenerTransacciones() {
        return transaccionRepository.findAll();
    }

    @Override
    public Transaccion crearTransaccion(Transaccion transaccion) {
        // 🔹 VALIDACIONES MANUALES
        if (transaccion == null) {
            throw new IllegalArgumentException("La transacción no puede ser nula");
        }

        if (transaccion.getTipo() == null || transaccion.getTipo().isBlank()) {
            throw new IllegalArgumentException("El tipo de transacción es obligatorio");
        }

        if (!transaccion.getTipo().equalsIgnoreCase("DEPOSITO")
                && !transaccion.getTipo().equalsIgnoreCase("RETIRO")
                && !transaccion.getTipo().equalsIgnoreCase("TRANSFERENCIA")) {
            throw new IllegalArgumentException("Tipo de transacción inválido (DEPOSITO, RETIRO, TRANSFERENCIA)");
        }

        if (transaccion.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que 0");
        }

        if (transaccion.getCuentaOrigen() == null) {
            throw new IllegalArgumentException("Debe especificar la cuenta origen");
        }

        // Si es transferencia, debe haber cuenta destino y diferente de la origen
        if (transaccion.getTipo().equalsIgnoreCase("TRANSFERENCIA")) {
            if (transaccion.getCuentaDestino() == null) {
                throw new IllegalArgumentException("Debe especificar la cuenta destino para la transferencia");
            }
            if (transaccion.getCuentaOrigen().getId().equals(transaccion.getCuentaDestino().getId())) {
                throw new IllegalArgumentException("La cuenta origen y destino no pueden ser la misma");
            }
        }

        // Asignar fecha automática si no se envía
        if (transaccion.getFecha() == null) {
            transaccion.setFecha(LocalDateTime.now());
        }

        return transaccionRepository.save(transaccion);
    }
}
