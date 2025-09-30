package com.example.banco.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre del pago es obligatorio")
    private String nombrePago;

    @Column(nullable = false)
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor que cero")
    private Double monto;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;

    @Column(nullable = false)
    @NotNull(message = "La fecha del pago es obligatoria")
    private LocalDateTime fechaPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "El cliente es obligatorio")
    private Cliente cliente;

}
