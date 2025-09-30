package com.example.banco.dto;

import lombok.Data;

@Data
public class CuentaCreateDTO {
    private int numeroCuenta;
    private Long clienteId; // solo pasamos el id del cliente al crear/actualizar
}
