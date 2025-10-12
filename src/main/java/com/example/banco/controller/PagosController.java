package com.example.banco.controller;

import com.example.banco.dto.PagosDTO;
import com.example.banco.dto.PagosCreateDTO;
import com.example.banco.mapper.PagosMapper;
import com.example.banco.modelo.Pagos;
import com.example.banco.service.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pagos")
@CrossOrigin(origins = "*")
public class PagosController {

    @Autowired
    private PagosService pagosService;

    @Autowired
    private PagosMapper pagosMapper;

    @GetMapping
    public List<PagosDTO> obtenerTodos() {
        return pagosService.obtenerTodas()
                .stream()
                .map(pagosMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public PagosDTO crearPago(@RequestBody PagosCreateDTO nuevoPagoDTO) {
        Pagos pago = pagosMapper.toEntity(nuevoPagoDTO);
        return pagosMapper.toDTO(pagosService.crearPagos(pago));
    }
}
