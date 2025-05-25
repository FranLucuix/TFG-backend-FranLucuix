/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado;

import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author francis
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    @Value("${imagenes.ruta}")
    private String directorioImagenes;

     @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Convierte la ruta relativa a absoluta, basada en la carpeta desde donde se ejecuta la app
        String rutaAbsoluta = Paths.get(directorioImagenes).toAbsolutePath().toUri().toString();

        registry.addResourceHandler("/uploads/img/productos/**")
                .addResourceLocations(rutaAbsoluta);
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry){
    registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
            .allowCredentials(true)
            .allowedHeaders("*");
    }
    
    
    
}
