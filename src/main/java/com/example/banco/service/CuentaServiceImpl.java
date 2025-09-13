package com.example.banco.service;

import com.example.banco.modelo.Cuenta;
import com.example.banco.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<Cuenta> obtenerCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con id: " + id));
    }

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        if (cuenta.getCliente() == null) {
            throw new RuntimeException("La cuenta debe estar asociada a un cliente.");
        }
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta actualizarCuenta(Long id, Cuenta cuentaActualizada) {
        Cuenta cuenta = obtenerCuentaPorId(id);
        cuenta.setNumeroCuenta(cuentaActualizada.getNumeroCuenta());
        cuenta.setCliente(cuentaActualizada.getCliente());
        cuenta.setTransacciones(cuentaActualizada.getTransacciones());
        return cuentaRepository.save(cuenta);
    }

    @Override
    public void eliminarCuenta(Long id) {
        if (!cuentaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. La cuenta no existe.");
        }
        cuentaRepository.deleteById(id);
    }
}
