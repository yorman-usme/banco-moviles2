package com.example.banco.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String correoElectronico;
    private String password;
}
