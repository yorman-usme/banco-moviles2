package com.example.banco.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "El tipo de transacción es obligatorio")
    @Pattern(regexp = "DEPOSITO|RETIRO|TRANSFERENCIA", message = "El tipo debe ser DEPOSITO, RETIRO o TRANSFERENCIA")
    private String tipo;

    @Column(nullable = false)
    @Positive(message = "El monto debe ser mayor que cero")
    private double monto;

    private LocalDateTime fecha = LocalDateTime.now();

    private String cuentaOrigenNumero;
    private String cuentaDestinoNumero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    @NotNull(message = "La cuenta es obligatoria")
    private Cuenta cuenta;
}
