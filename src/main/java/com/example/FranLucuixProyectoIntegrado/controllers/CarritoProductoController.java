/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.controllers;

import com.example.FranLucuixProyectoIntegrado.DTOs.CarritoProductoDTO;
import com.example.FranLucuixProyectoIntegrado.DTOs.ProductoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.CarritoProducto;
import com.example.FranLucuixProyectoIntegrado.entities.Producto;
import com.example.FranLucuixProyectoIntegrado.services.CarritoProductoService;
import com.example.FranLucuixProyectoIntegrado.services.ProductoService;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("/carritoProductos")
public class CarritoProductoController {

    @Autowired
    private CarritoProductoService carritoProductoService;

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<CarritoProductoDTO> getAllCarritoProductos() {
        return carritoProductoService.findAll();
    }

    @PostMapping
    public ResponseEntity<CarritoProductoDTO> addCarritoProducto(@RequestBody @Valid CarritoProductoDTO dto) {
        ProductoDTO producto = productoService.findById(dto.getIdProducto());

        if (dto.getCantidad() > producto.getStock()) {
            throw new IllegalArgumentException("Cantidad solicitada supera el stock disponible");
        }

        producto.setStock(producto.getStock() - dto.getCantidad());
        productoService.updateProduct(producto.getIdProducto(), producto);

        CarritoProductoDTO creado = carritoProductoService.addCarritoProducto(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping("/{idCarrito}/{idProducto}")
    public ResponseEntity<CarritoProductoDTO> getById(
            @PathVariable int idCarrito,
            @PathVariable int idProducto) {

        CarritoProductoDTO dto = carritoProductoService.findById(idCarrito, idProducto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{idCarrito}/{idProducto}")
    public ResponseEntity<?> update(
            @PathVariable int idCarrito,
            @PathVariable int idProducto,
            @RequestBody @Valid CarritoProductoDTO dto) {

        if (dto.getIdCarrito() != idCarrito || dto.getIdProducto() != idProducto) {
            return ResponseEntity.badRequest().body("Los IDs del path y del cuerpo no coinciden.");
        }

        try {
            CarritoProductoDTO updated = carritoProductoService.editCarritoProducto(dto);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Aquí se captura si supera stock
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al actualizar el carrito.");
        }
    }

    @DeleteMapping("/{idCarrito}/{idProducto}")
    public ResponseEntity<Void> delete(
            @PathVariable int idCarrito,
            @PathVariable int idProducto) {

        carritoProductoService.deleteCarritoProducto(idCarrito, idProducto);
        return ResponseEntity.noContent().build();
    }

}
