/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.controllers;

import com.example.FranLucuixProyectoIntegrado.DTOs.CarritoProductoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.CarritoProducto;
import com.example.FranLucuixProyectoIntegrado.services.CarritoProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francis
 */
@RestController
@RequestMapping("/carritoProductos")
public class CarritoProductoController {

    @Autowired
    private CarritoProductoService carritoProductoService;

    @GetMapping
    public List<CarritoProductoDTO> getAllCarritoProductos() {
        return carritoProductoService.findAll();
    }
}

