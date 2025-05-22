/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.services;

import com.example.FranLucuixProyectoIntegrado.DTOs.MetodoPagoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.MetodoPago;
import com.example.FranLucuixProyectoIntegrado.repositories.IMetodoPagoRepository;
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
public class MetodoPagoService {

    @Autowired
    private IMetodoPagoRepository metodoPagoRepository;

    @Autowired
    private DTOConverter dtoConverter;

    public List<MetodoPagoDTO> findAll() {
        List<MetodoPago> metodos = metodoPagoRepository.findAll();
        List<MetodoPagoDTO> metodosDTO = new ArrayList<>();

        for (MetodoPago m : metodos) {
            metodosDTO.add(dtoConverter.metodoPagoToDTO(m));
        }

        return metodosDTO;
    }

   

    public MetodoPagoDTO findById(int id) {
        MetodoPago metodo = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        return dtoConverter.metodoPagoToDTO(metodo);
    }

    @Transactional
    public MetodoPagoDTO createMetodoPago(MetodoPagoDTO dto) {
        MetodoPago metodo = dtoConverter.metodoPagoToEntity(dto);
        return dtoConverter.metodoPagoToDTO(metodoPagoRepository.save(metodo));
    }

    @Transactional
    public MetodoPagoDTO updateMetodoPago(int id, MetodoPagoDTO dto) {
        MetodoPago existing = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

        existing.setTipo(dto.getTipo());

        return dtoConverter.metodoPagoToDTO(metodoPagoRepository.save(existing));
    }

    @Transactional
    public void deleteMetodoPago(int id) {
        if (!metodoPagoRepository.existsById(id)) {
            throw new RuntimeException("Método de pago no encontrado");
        }
        metodoPagoRepository.deleteById(id);
    }
}
