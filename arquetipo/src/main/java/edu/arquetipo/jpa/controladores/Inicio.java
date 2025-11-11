package edu.arquetipo.jpa.controladores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicializa y arranca la aplicación Spring Boot.
 * Spring se encarga automáticamente de:
 * 1. Levantar el servidor web (por defecto, Tomcat).
 * 2. Escanear y mapear todos los controladores (@RestController).
 * 3. Gestionar la inyección de dependencias (@Autowired).
 */
@SpringBootApplication
public class Inicio {

    public static void main(String[] args) {
        // Arranca la aplicación Spring Boot.
        // Todos los controladores REST estarán disponibles en el puerto por defecto
        // (8080)
        // o el que definas en application.properties.
        SpringApplication.run(Inicio.class, args);
    }
}