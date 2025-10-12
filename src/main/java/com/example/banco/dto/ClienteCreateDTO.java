package com.example.banco.dto;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class ClienteCreateDTO {
    @NotBlank(message = "El nombre es requerido")
    private String nombre;
    
    @NotBlank(message = "El apellido es requerido")
    private String apellido;
    
    @NotBlank(message = "El correo es requerido")
    @Email(message = "El formato del correo no es válido")
    private String correoElectronico;
    
    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
    
    @NotBlank(message = "La identificación es requerida")
    private String identificacion;
    
    @NotBlank(message = "El teléfono es requerido")
    private String telefono;
}
