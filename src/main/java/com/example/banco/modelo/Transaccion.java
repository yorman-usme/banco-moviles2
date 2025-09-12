package com.example.banco.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "DEPOSITO", "RETIRO", "TRANSFERENCIA"

    private double monto;

    private LocalDateTime fecha;

    // Relación con Cuenta
    @ManyToOne
    private Cuenta cuentaOrigen;

    // Para transferencias puedes agregar cuentaDestino (opcional)
    @ManyToOne
    private Cuenta cuentaDestino;
}
