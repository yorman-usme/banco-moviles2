package com.example.banco.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PagosCreateDTO {

    @NotBlank(message = "El nombre del pago es obligatorio")
    @Size(max = 100, message = "El nombre del pago no puede tener más de 100 caracteres")
    private String nombrePago;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor que cero")
    private Double monto;

    @NotBlank(message = "El método de pago es obligatorio")
    @Size(max = 50, message = "El método de pago no puede tener más de 50 caracteres")
    private String metodoPago;

    @NotNull(message = "El ID del cliente es obligatorio")
    @Min(value = 1, message = "El ID del cliente debe ser un número válido mayor que cero")
    private Long clienteId;
}
