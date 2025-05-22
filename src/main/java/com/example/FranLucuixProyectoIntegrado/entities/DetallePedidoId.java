/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author francis
 */
@Embeddable
public class DetallePedidoId implements Serializable{
    
    private Integer idPedido;
    private Integer idProducto;

    // Constructores
    public DetallePedidoId() {}

    public DetallePedidoId(Integer idpedido, Integer idproducto) {
        this.idPedido = idpedido;
        this.idProducto = idproducto;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.idPedido);
        hash = 11 * hash + Objects.hashCode(this.idProducto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetallePedidoId other = (DetallePedidoId) obj;
        if (!Objects.equals(this.idPedido, other.idPedido)) {
            return false;
        }
        return Objects.equals(this.idProducto, other.idProducto);
    }
    
    
}
