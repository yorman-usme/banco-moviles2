package com.example.banco.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.banco.modelo.Cuenta;
import com.example.banco.repository.CuentaRepositrory;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepositrory cuentaRepository;

    @Override
    public List<Cuenta> obtenerCuentas() {
       return cuentaRepository.findAll();
    }

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
              return cuentaRepository.save(cuenta);
   }
    
}
