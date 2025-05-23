/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.services;

import com.example.FranLucuixProyectoIntegrado.DTOs.CarritoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.Carrito;
import com.example.FranLucuixProyectoIntegrado.entities.Usuario;
import com.example.FranLucuixProyectoIntegrado.repositories.ICarritoRepository;
import com.example.FranLucuixProyectoIntegrado.repositories.IUsuarioRepository;
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
    private DTOConverter dtoConverter;
    
    @Autowired
    private IUsuarioRepository usuarioRepository;

    public List<CarritoDTO> findAll() {
        List<Carrito> carritos = carritoRepository.findAll();
        List<CarritoDTO> carritosDTO = new ArrayList<>();

        for (Carrito c : carritos) {
            carritosDTO.add(dtoConverter.carritoToDTO(c));
        }

        return carritosDTO;
    }

    public CarritoDTO saveCarrito(CarritoDTO dto) {
        Carrito carrito = dtoConverter.carritoToEntity(dto);

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        carrito.setUsuario(usuario);

        Carrito saved = carritoRepository.save(carrito);
        return dtoConverter.carritoToDTO(saved);
    }

    public CarritoDTO getCarritoById(int id) {
        Carrito carrito = carritoRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        return dtoConverter.carritoToDTO(carrito);
    }
    
     public void deleteCarrito(int id) {
        carritoRepository.deleteById(id);
    }
}
