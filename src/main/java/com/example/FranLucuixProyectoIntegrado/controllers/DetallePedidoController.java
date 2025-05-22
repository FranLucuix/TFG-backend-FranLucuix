/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.controllers;

import com.example.FranLucuixProyectoIntegrado.DTOs.DetallePedidoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.DetallePedido;
import com.example.FranLucuixProyectoIntegrado.services.DetallePedidoService;
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
@RequestMapping("/detallesPedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping
    public List<DetallePedidoDTO> getAllDetallesPedido() {
        return detallePedidoService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<DetallePedidoDTO> createDetallePedido(@RequestBody @Valid DetallePedidoDTO dto) {
        DetallePedidoDTO created = detallePedidoService.createDetallePedido(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{idPedido}/{idProducto}")
    public ResponseEntity<DetallePedidoDTO> updateDetallePedido(
            @PathVariable int idPedido,
            @PathVariable int idProducto,
            @RequestBody @Valid DetallePedidoDTO dto) {

        DetallePedidoDTO updated = detallePedidoService.updateDetallePedido(idPedido, idProducto, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{idPedido}/{idProducto}")
    public ResponseEntity<Void> deleteDetallePedido(
            @PathVariable int idPedido,
            @PathVariable int idProducto) {

        detallePedidoService.deleteDetallePedido(idPedido, idProducto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idPedido}/{idProducto}")
    public ResponseEntity<DetallePedidoDTO> getDetallePedido(
            @PathVariable int idPedido,
            @PathVariable int idProducto) {

        DetallePedidoDTO dto = detallePedidoService.findById(idPedido, idProducto);
        return ResponseEntity.ok(dto);
    }
}

