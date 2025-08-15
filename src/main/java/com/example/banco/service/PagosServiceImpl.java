package com.example.banco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.banco.modelo.Pagos;
import com.example.banco.repository.PagosRepository;



@Service
public class PagosServiceImpl implements PagosService {

    private PagosRepository pagosRepository;

    public PagosServiceImpl(PagosRepository pagosRepository) {
        this.pagosRepository = pagosRepository;
    }

    @Override
    public List<Pagos> obtenerTodas() {
        return pagosRepository.findAll();
    }

    @Override
    public Pagos crearPagos(Pagos pagos) {
        return pagosRepository.save(pagos);
    }

}
