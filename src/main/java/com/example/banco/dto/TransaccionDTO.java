package com.example.banco.dto;

import lombok.Data;

@Data
public class TransaccionDTO {
    private Long id;
    private String tipo;
    private double monto;
    private Long cuentaId; // solo devolvemos el id de la cuenta
}
