package com.example.banco.dto;

import lombok.Data;

@Data
public class TransaccionCreateDTO {
    private String tipo;
    private double monto;
    private Long cuentaId; // al crear solo pasamos el id de la cuenta
}
