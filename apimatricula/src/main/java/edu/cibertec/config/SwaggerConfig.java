package edu.cibertec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * 📘 Configuración global de Swagger / OpenAPI
 *
 * Expone documentación interactiva en:
 * ➜ http://localhost:8080/swagger-ui/index.html
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - Sistema de Matrícula")
                        .version("1.0.0")
                        .description("""
                            API desarrollada con Spring Boot 3 para la gestión de cursos y matrículas.
                            Incluye endpoints para registrar, actualizar, listar y eliminar cursos.
                            """)
                        .contact(new Contact()
                                .name("Ing. Styp Canto - Cibertec - Escuela Profesional")
                                .email("soporte@cibertec.edu.pe")
                                .url("https://www.cibertec.edu.pe"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}