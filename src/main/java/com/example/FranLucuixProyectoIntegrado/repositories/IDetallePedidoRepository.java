/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.repositories;

import com.example.FranLucuixProyectoIntegrado.entities.DetallePedido;
import com.example.FranLucuixProyectoIntegrado.entities.DetallePedidoId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author francis
 */
public interface IDetallePedidoRepository extends JpaRepository<DetallePedido, DetallePedidoId> {
    
}
