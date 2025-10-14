package com.example.banco.dto;


import lombok.Data;

@Data
public class CuentaDTO {
    private Long id;
    private String numeroCuenta;
    private Long clienteId; 
}
