package com.example.banco.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TransaccionCreateDTO {

    @NotBlank(message = "El tipo de transacción es obligatorio")
    @Pattern(
        regexp = "^(DEPOSITO|RETIRO|TRANSFERENCIA)$",
        message = "El tipo de transacción debe ser DEPOSITO, RETIRO o TRANSFERENCIA"
    )
    private String tipo;

    @Positive(message = "El monto debe ser mayor que cero")
    private double monto;

    @NotNull(message = "El ID de la cuenta es obligatorio")
    @Min(value = 1, message = "El ID de la cuenta debe ser un número válido mayor que cero")
    private Long cuentaId;
}
