package com.example.banco.dto;

import lombok.Data;

@Data
public class ClienteCreateDTO {
    private String identificacion;
    private String name;
    private String apellido;
    private String direccion;
    private String telefono;
    private String correo_electronico;
    private String fecha_nacimiento;
}