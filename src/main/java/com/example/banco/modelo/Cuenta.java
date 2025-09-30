package com.example.banco.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Min(value = 1, message = "El número de cuenta debe ser mayor que cero")
    private int numeroCuenta;

    // Relación con Cliente (muchas cuentas pueden ser de un cliente)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Relación con Transacciones (una cuenta puede tener varias transacciones)
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaccion> transacciones;
}
