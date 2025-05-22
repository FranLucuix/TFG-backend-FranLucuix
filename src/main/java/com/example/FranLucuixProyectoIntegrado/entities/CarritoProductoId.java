/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author francis
 */
@Embeddable
public class CarritoProductoId implements Serializable {

    private int idCarrito;
    private int idProducto;

    public CarritoProductoId() {
    }

    public CarritoProductoId(int idCarrito, int idProducto) {
        this.idCarrito = idCarrito;
        this.idProducto = idProducto;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.idCarrito;
        hash = 59 * hash + this.idProducto;
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
        final CarritoProductoId other = (CarritoProductoId) obj;
        if (this.idCarrito != other.idCarrito) {
            return false;
        }
        return this.idProducto == other.idProducto;
    }
    
    
}
