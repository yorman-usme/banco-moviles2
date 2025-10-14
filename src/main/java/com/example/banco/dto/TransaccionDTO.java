package com.example.banco.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransaccionDTO {
    private Long id;
    private String tipo;
    private double monto;
    private LocalDateTime fecha;
    private Long cuentaId; 
}
