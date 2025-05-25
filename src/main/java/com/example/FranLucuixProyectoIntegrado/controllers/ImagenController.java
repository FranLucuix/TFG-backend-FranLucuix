package com.example.FranLucuixProyectoIntegrado.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/api/imagenes")
@CrossOrigin(origins = "http://localhost:4200") // Permitir solicitudes desde Angular
public class ImagenController {

    private static final String DIRECTORIO_IMAGENES = "uploads/img/productos/";

    
    @Value("${imagenes.ruta}")
    private String directorioImagenes;

    @PostMapping("/subir")
    public ResponseEntity<String> subirImagen(@RequestParam("archivo") MultipartFile archivo) {
        if (archivo.isEmpty()) {
            return ResponseEntity.badRequest().body("Archivo vacío");
        }

        try {
            Path directorio = Paths.get(directorioImagenes);
            if (!Files.exists(directorio)) {
                Files.createDirectories(directorio);
            }
            String nombreOriginal = archivo.getOriginalFilename();
            String nombreFinal = generarNombreUnico(nombreOriginal);
            Path rutaArchivo = directorio.resolve(nombreFinal);
            Files.write(rutaArchivo, archivo.getBytes());

            String urlPublica = "/uploads/img/productos/" + nombreFinal;
            return ResponseEntity.ok(urlPublica);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen");
        }
    }

    private String generarNombreUnico(String nombreOriginal) {
        String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf('.'));
        String baseNombre = nombreOriginal.substring(0, nombreOriginal.lastIndexOf('.'));
        String nombreFinal = baseNombre + "_" + System.currentTimeMillis() + extension;
        return nombreFinal.replaceAll("\\s+", "_");
    }
}
