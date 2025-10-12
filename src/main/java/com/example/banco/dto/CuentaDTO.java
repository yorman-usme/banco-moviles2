package com.example.banco.dto;

import com.example.banco.modelo.Cuenta;

import lombok.Data;

@Data
public class CuentaDTO {
    public CuentaDTO(Cuenta cuenta) {
        //TODO Auto-generated constructor stub
    }
    private Long id;
    private int numeroCuenta;
    private Long clienteId; 
}
