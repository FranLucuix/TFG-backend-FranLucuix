/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.services;

import com.example.FranLucuixProyectoIntegrado.DTOs.CarritoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.Carrito;
import com.example.FranLucuixProyectoIntegrado.repositories.ICarritoRepository;
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
public class CarritoService {

    @Autowired
    private ICarritoRepository carritoRepository;

    @Autowired
    private DTOConverter dtos;

    public List<CarritoDTO> findAll() {
        List<Carrito> carritos = carritoRepository.findAll();
        List<CarritoDTO> carritosDTO = new ArrayList<>();

        for (Carrito c : carritos) {
            carritosDTO.add(dtos.carritoToDTO(c));
        }

        return carritosDTO;
    }

    public Optional<Carrito> findById(Integer id) {
        return carritoRepository.findById(id);
    }

    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public Carrito update(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void deleteById(Integer id) {
        carritoRepository.deleteById(id);
    }
}
