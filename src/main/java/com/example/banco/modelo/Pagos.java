package com.example.banco.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagos {

    
    @Id // Indica que este atributo es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String name;

}
