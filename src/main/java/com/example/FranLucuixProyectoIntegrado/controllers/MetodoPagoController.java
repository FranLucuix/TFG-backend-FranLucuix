/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.controllers;

import com.example.FranLucuixProyectoIntegrado.DTOs.MetodoPagoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.MetodoPago;
import com.example.FranLucuixProyectoIntegrado.services.MetodoPagoService;
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
@RequestMapping("/pagos")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public List<MetodoPagoDTO> getAllMetodosPago() {
        return metodoPagoService.findAll();
    }
    
     @PostMapping
    public ResponseEntity<MetodoPagoDTO> createMetodoPago(@RequestBody @Valid MetodoPagoDTO dto) {
        MetodoPagoDTO created = metodoPagoService.createMetodoPago(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> updateMetodoPago(@PathVariable int id, @RequestBody @Valid MetodoPagoDTO dto) {
        MetodoPagoDTO updated = metodoPagoService.updateMetodoPago(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetodoPago(@PathVariable int id) {
        metodoPagoService.deleteMetodoPago(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> getMetodoPago(@PathVariable int id) {
        MetodoPagoDTO metodo = metodoPagoService.findById(id);
        return ResponseEntity.ok(metodo);
    }
}

