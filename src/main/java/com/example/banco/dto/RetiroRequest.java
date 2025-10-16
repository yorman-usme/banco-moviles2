package com.example.banco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RetiroRequest {
       // Se requiere el número de cuenta de donde se retirará (la del usuario logueado)
    @NotBlank(message = "El número de cuenta es obligatorio")
    private String numeroCuenta;
    
    // El monto debe ser un valor positivo y es obligatorio
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor que cero")
    private Double monto;
}
