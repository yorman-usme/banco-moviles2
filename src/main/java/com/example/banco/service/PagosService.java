package com.example.banco.service;

import java.util.List;
import com.example.banco.modelo.Pagos;

public interface PagosService {
    List<Pagos> obtenerTodas();
    Pagos crearPagos(Pagos pagos);
}
