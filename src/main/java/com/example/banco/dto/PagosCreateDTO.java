package com.example.banco.dto;

import lombok.Data;

@Data
public class PagosCreateDTO {
    private String nombrePago;
    private Double monto;
    private String metodoPago;
    private Long clienteId; // cuando se crea, pasamos el id del cliente
}
