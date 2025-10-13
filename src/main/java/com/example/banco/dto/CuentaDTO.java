package com.example.banco.dto;

import com.example.banco.modelo.Cuenta;

import lombok.Data;

@Data
public class CuentaDTO {
    public CuentaDTO(Cuenta cuenta) {
        this.id = cuenta.getId();
        this.numeroCuenta = String.valueOf(cuenta.getNumeroCuenta());
        this.clienteId = cuenta.getCliente().getId();
    }
    private Long id;
    private String numeroCuenta;
    private Long clienteId; 
}
