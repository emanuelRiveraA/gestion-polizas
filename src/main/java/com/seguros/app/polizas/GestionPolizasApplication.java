package com.seguros.app.polizas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GestionPolizasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionPolizasApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/api/**")                       // URLs que permiten CORS
	                    .allowedOrigins("http://localhost:4200")     // Orígenes permitidos (Angular)
	                    .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
	                    .allowedHeaders("*")                          // Cabeceras permitidas
	                    .allowCredentials(true)                       // Permite enviar cookies/autenticación
	                    .maxAge(3600);                                // Tiempo que dura la configuración en cache
	        }
	    };
	}


}
