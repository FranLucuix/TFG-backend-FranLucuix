/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.controllers;

import com.example.FranLucuixProyectoIntegrado.DTOs.CarritoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.Carrito;
import com.example.FranLucuixProyectoIntegrado.services.CarritoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francis
 */
@RestController
@RequestMapping("/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<CarritoDTO> getAllCarritos() {
        return carritoService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<CarritoDTO> createCarrito(@RequestBody @Valid CarritoDTO dto) {
        CarritoDTO created = carritoService.saveCarrito(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> getCarrito(@PathVariable int id) {
        CarritoDTO dto = carritoService.getCarritoById(id);
        return ResponseEntity.ok(dto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CarritoDTO> updateCarrito(@PathVariable int id, @RequestBody CarritoDTO dto) {
        dto.setIdCarrito(id);
        CarritoDTO updated = carritoService.saveCarrito(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrito(@PathVariable int id) {
        carritoService.deleteCarrito(id);
        return ResponseEntity.noContent().build();
    }
}

