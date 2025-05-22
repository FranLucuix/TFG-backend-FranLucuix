/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.services;

import com.example.FranLucuixProyectoIntegrado.DTOs.CarritoProductoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.CarritoProducto;
import com.example.FranLucuixProyectoIntegrado.entities.CarritoProductoId;
import com.example.FranLucuixProyectoIntegrado.repositories.ICarritoProductoRepository;
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
public class CarritoProductoService {

    @Autowired
    private ICarritoProductoRepository carritoProductoRepository;

    @Autowired
    private DTOConverter dtos;

    public List<CarritoProductoDTO> findAll() {
        List<CarritoProducto> cps = carritoProductoRepository.findAll();
        List<CarritoProductoDTO> cpsDTO = new ArrayList<>();

        for (CarritoProducto cp : cps) {
            cpsDTO.add(dtos.carritoProductoToDTO(cp));
        }

        return cpsDTO;
    }

    public Optional<CarritoProducto> findById(CarritoProductoId id) {
        return carritoProductoRepository.findById(id);
    }

    public CarritoProducto save(CarritoProducto carritoProducto) {
        return carritoProductoRepository.save(carritoProducto);
    }

    public CarritoProducto update(CarritoProducto carritoProducto) {
        return carritoProductoRepository.save(carritoProducto);
    }

    public void deleteById(CarritoProductoId id) {
        carritoProductoRepository.deleteById(id);
    }
}
