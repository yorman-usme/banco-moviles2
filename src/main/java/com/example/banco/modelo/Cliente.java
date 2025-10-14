package com.example.banco.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identificacion;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String password; // Nuevo campo

    @Column(nullable = false, unique = true)
    private String correoElectronico;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "direccion", nullable = true, length = 100)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    @Column(name = "fecha_nacimiento", nullable = true, length = 10)
    private String fecha_nacimiento; 

    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;
}
