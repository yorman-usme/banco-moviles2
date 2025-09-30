package com.example.banco.dto;

import lombok.Data;

@Data
public class CuentaDTO {
    private Long id;
    private int numeroCuenta;
    private Long clienteId; // en vez de devolver el objeto Cliente entero
}
