/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.services;

import com.example.FranLucuixProyectoIntegrado.DTOs.CarritoProductoDTO;
import com.example.FranLucuixProyectoIntegrado.DTOs.ProductoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.CarritoProducto;
import com.example.FranLucuixProyectoIntegrado.entities.CarritoProductoId;
import com.example.FranLucuixProyectoIntegrado.entities.Producto;
import com.example.FranLucuixProyectoIntegrado.repositories.ICarritoProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author francis
 */
@Service
public class CarritoProductoService {

    @Autowired
    private ICarritoProductoRepository carritoProductoRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private DTOConverter dtoConverter;

    public List<CarritoProductoDTO> findAll() {
        List<CarritoProducto> cps = carritoProductoRepository.findAll();
        List<CarritoProductoDTO> cpsDTO = new ArrayList<>();

        for (CarritoProducto cp : cps) {
            cpsDTO.add(dtoConverter.carritoProductoToDTO(cp));
        }

        return cpsDTO;
    }

    public CarritoProductoDTO findById(int idCarrito, int idProducto) {
        CarritoProductoId id = new CarritoProductoId(idCarrito, idProducto);
        CarritoProducto cp = carritoProductoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado en el carrito"));

        return dtoConverter.carritoProductoToDTO(cp);
    }

    @Transactional
    public CarritoProductoDTO addCarritoProducto(CarritoProductoDTO dto) {
        CarritoProducto entity = dtoConverter.carritoProductoToEntity(dto);
        return dtoConverter.carritoProductoToDTO(carritoProductoRepository.save(entity));
    }

    @Transactional
    public CarritoProductoDTO editCarritoProducto(CarritoProductoDTO dto) {
        CarritoProductoId id = new CarritoProductoId(dto.getIdCarrito(), dto.getIdProducto());
        CarritoProducto existente = carritoProductoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado en el carrito"));

        int cantidadAnterior = existente.getCantidad();
        int cantidadNueva = dto.getCantidad();
        int diferencia = cantidadNueva - cantidadAnterior;

        ProductoDTO producto = productoService.findById(dto.getIdProducto());

        if (diferencia > 0) {
            // Se está aumentando la cantidad en el carrito → quitar del stock
            if (diferencia > producto.getStock()) {
                throw new IllegalArgumentException("Cantidad solicitada supera el stock disponible");
            }
            producto.setStock(producto.getStock() - diferencia);
        } else if (diferencia < 0) {
            // Se está reduciendo la cantidad en el carrito → devolver al stock
            producto.setStock(producto.getStock() + Math.abs(diferencia));
        }

        productoService.updateProduct(producto.getIdProducto(), producto);

        existente.setCantidad(cantidadNueva);
        return dtoConverter.carritoProductoToDTO(carritoProductoRepository.save(existente));
    }

    @Transactional
    public void deleteCarritoProducto(int idCarrito, int idProducto) {
        CarritoProductoId id = new CarritoProductoId(idCarrito, idProducto);

        if (!carritoProductoRepository.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado en el carrito");
        }

        carritoProductoRepository.deleteById(id);
    }

}
