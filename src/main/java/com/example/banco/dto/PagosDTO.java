package com.example.banco.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PagosDTO {
    private Long id;
    private String nombrePago;
    private Double monto;
    private String metodoPago;
    private LocalDateTime fechaPago;
    private Long clienteId; 
}
