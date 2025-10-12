package com.example.banco.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Banco - Proyecto de Ejemplo")
                .version("1.0.0")
                .description("Documentación interactiva de la API del sistema bancario desarrollada en Spring Boot.")
                .contact(new Contact()
                    .name("Tu Nombre")
                    .email("tu@email.com")))
            .servers(List.of(
                new Server()
                    .url("http://localhost:8080")
                    .description("Servidor Local")
            ));
    }
}
