/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.services;

import com.example.FranLucuixProyectoIntegrado.DTOs.DetallePedidoDTO;
import com.example.FranLucuixProyectoIntegrado.entities.DetallePedido;
import com.example.FranLucuixProyectoIntegrado.entities.DetallePedidoId;
import com.example.FranLucuixProyectoIntegrado.repositories.IDetallePedidoRepository;
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
public class DetallePedidoService {

    @Autowired
    private IDetallePedidoRepository detallePedidoRepository;

    @Autowired
    private DTOConverter dtoConverter;

    public List<DetallePedidoDTO> findAll() {
        List<DetallePedido> detalles = detallePedidoRepository.findAll();
        List<DetallePedidoDTO> detallesDTO = new ArrayList<>();

        for (DetallePedido d : detalles) {
            detallesDTO.add(dtoConverter.detallePedidoToDTO(d));
        }

        return detallesDTO;
    }

    public DetallePedidoDTO findById(int idPedido, int idProducto) {
        DetallePedidoId id = new DetallePedidoId(idPedido, idProducto);
        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado"));
        return dtoConverter.detallePedidoToDTO(detalle);
    }

    @Transactional
    public DetallePedidoDTO createDetallePedido(DetallePedidoDTO dto) {
        DetallePedido detalle = dtoConverter.detallePedidoToEntity(dto);
        return dtoConverter.detallePedidoToDTO(detallePedidoRepository.save(detalle));
    }

    @Transactional
    public DetallePedidoDTO updateDetallePedido(int idPedido, int idProducto, DetallePedidoDTO dto) {
        DetallePedidoId id = new DetallePedidoId(idPedido, idProducto);
        DetallePedido existing = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado"));

        existing.setCantidad(dto.getCantidad());
        existing.setPrecioUnitario(dto.getPrecioUnitario());

        return dtoConverter.detallePedidoToDTO(detallePedidoRepository.save(existing));
    }

    @Transactional
    public void deleteDetallePedido(int idPedido, int idProducto) {
        DetallePedidoId id = new DetallePedidoId(idPedido, idProducto);
        if (!detallePedidoRepository.existsById(id)) {
            throw new RuntimeException("Detalle de pedido no encontrado");
        }
        detallePedidoRepository.deleteById(id);
    }
}
