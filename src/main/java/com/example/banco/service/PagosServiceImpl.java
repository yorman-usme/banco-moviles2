package com.example.banco.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.banco.modelo.Pagos;
import com.example.banco.repository.PagosRepository;

@Service
public class PagosServiceImpl implements PagosService {

    private final PagosRepository pagosRepository;

    public PagosServiceImpl(PagosRepository pagosRepository) {
        this.pagosRepository = pagosRepository;
    }

    @Override
    public List<Pagos> obtenerTodas() {
        return pagosRepository.findAll();
    }

    @Override
    public Pagos crearPagos(Pagos pago) {

        if (pago.getNombrePago() == null || pago.getNombrePago().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del pago no puede estar vacío");
        }

        if (pago.getMonto() == null || pago.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que 0");
        }

        if (pago.getMetodoPago() == null || pago.getMetodoPago().trim().isEmpty()) {
            throw new IllegalArgumentException("El método de pago no puede estar vacío");
        }

        if (pago.getCliente() == null) {
            throw new IllegalArgumentException("El pago debe estar asociado a un cliente válido");
        }

        pago.setFechaPago(LocalDateTime.now());

        return pagosRepository.save(pago);
    }
}
