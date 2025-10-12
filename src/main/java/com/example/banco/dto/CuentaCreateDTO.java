package com.example.banco.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CuentaCreateDTO {

    @Min(value = 1, message = "El número de cuenta debe ser un número positivo mayor que cero")
    private int numeroCuenta;

    @NotNull(message = "El ID del cliente es obligatorio")
    @Min(value = 1, message = "El ID del cliente debe ser un número válido mayor que cero")
    private Long clienteId;
}
