package com.tbd_grupo_8.lab_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Permitir el origen del frontend
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));

        // Permitir métodos HTTP
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        // Permitir todos los headers en las solicitudes
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

//        // Exponer el header Authorization para que esté accesible en el frontend
//        corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));

        // Exponer todos los headers en las respuestas
        corsConfiguration.addExposedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}