package com.example.banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
		System.out.println("=================================");
        System.out.println("🚀 SERVIDOR INICIADO CORRECTAMENTE");
        System.out.println("📍 URL: http://localhost:8080");
        System.out.println("📊 Base de datos: banco");
        System.out.println("=================================");
	}

}
