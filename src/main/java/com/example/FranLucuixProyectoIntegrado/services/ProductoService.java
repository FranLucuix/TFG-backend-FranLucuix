/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.services;

import com.example.FranLucuixProyectoIntegrado.DTOs.ProductoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.Producto;
import com.example.FranLucuixProyectoIntegrado.repositories.IProductoRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author francis
 */
@Service
public class ProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private DTOConverter dtoConverter;

    public List<ProductoDTO> findAll() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> productosDTO = new ArrayList<>();

        for (Producto p : productos) {
            productosDTO.add(dtoConverter.productoToDTO(p)); // Usa el método del conversor
        }

        return productosDTO;
    }

    public ProductoDTO findById(int id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return dtoConverter.productoToDTO(producto);
    }

    @Transactional
    public ProductoDTO createProduct(ProductoDTO dto) {
        Producto producto = dtoConverter.productoToEntity(dto);
        return dtoConverter.productoToDTO(productoRepository.save(producto));
    }

    @Transactional
    public ProductoDTO updateProduct(int id, ProductoDTO dto) {
        Producto existing = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setNombre(dto.getNombre());
        existing.setPrecio(dto.getPrecio());
        existing.setPrecioRebaja(dto.getPrecioRebajado());
        existing.setStock(dto.getStock());
        existing.setDescripcion(dto.getDescripcion());
        existing.setCategoria(dto.getCategoria());

        return dtoConverter.productoToDTO(productoRepository.save(existing));
    }

    @Transactional
    public void deleteProduct(int id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productoRepository.deleteById(id);
    }
}
