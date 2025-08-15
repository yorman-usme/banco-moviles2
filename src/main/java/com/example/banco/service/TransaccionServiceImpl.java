 package com.example.banco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banco.modelo.Transaccion;
import com.example.banco.repository.TransaccionesRepository;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    @Autowired
    private TransaccionesRepository transaccionesRepository;

    @Override
    public List<Transaccion> obtenerTodas() {
        return transaccionesRepository.findAll();
    }

    @Override
    public Transaccion crearTransaccion(Transaccion transaccion) {
return transaccionesRepository.save(transaccion);
    }
    

    
}