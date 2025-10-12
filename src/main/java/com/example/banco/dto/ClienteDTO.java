package com.example.banco.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String identificacion;
    private String telefono;
}