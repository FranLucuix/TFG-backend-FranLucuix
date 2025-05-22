/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.services;

import com.example.FranLucuixProyectoIntegrado.DTOs.PedidoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.MetodoPago;
import com.example.FranLucuixProyectoIntegrado.entities.Pedido;
import com.example.FranLucuixProyectoIntegrado.entities.Usuario;
import com.example.FranLucuixProyectoIntegrado.repositories.IPedidoRepository;
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
public class PedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private DTOConverter dtoConverter;

    public List<PedidoDTO> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> pedidosDTO = new ArrayList<>();

        for (Pedido p : pedidos) {
            pedidosDTO.add(dtoConverter.pedidoToDTO(p));
        }

        return pedidosDTO;
    }

    public PedidoDTO findById(int id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        return dtoConverter.pedidoToDTO(pedido);
    }

    @Transactional
    public PedidoDTO createPedido(PedidoDTO dto) {
        Pedido pedido = dtoConverter.pedidoToEntity(dto);
        return dtoConverter.pedidoToDTO(pedidoRepository.save(pedido));
    }

    @Transactional
    public PedidoDTO updatePedido(int id, PedidoDTO dto) {
        Pedido existing = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        existing.setFechapedido(dto.getFechaPedido());
        existing.setTotal(dto.getTotal());
        existing.setEstado(dto.getEstado());
        existing.setDireccion(dto.getDireccion());
        existing.setFechaEntrega(dto.getFechaEntrega());

        existing.setUsuario(new Usuario(dto.getIdUsuario()));
        existing.setMetodoPago(new MetodoPago(dto.getIdPago()));

        return dtoConverter.pedidoToDTO(pedidoRepository.save(existing));
    }

    @Transactional
    public void deletePedido(int id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido no encontrado");
        }
        pedidoRepository.deleteById(id);
    }
}
