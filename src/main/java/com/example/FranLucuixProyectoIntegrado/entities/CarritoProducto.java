/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

/**
 *
 * @author francis
 */
@Entity
public class CarritoProducto {

    @EmbeddedId
    private CarritoProductoId id;

    @ManyToOne
    @MapsId("idCarrito")
    private Carrito carrito;

    @ManyToOne
    @MapsId("idProducto")
    private Producto producto;

    private int cantidad;

    public CarritoProducto() {
    }

    public CarritoProducto(Carrito carrito, Producto producto, int cantidad) {
        this.id = new CarritoProductoId(carrito.getIdCarrito(), producto.getIdProducto());
        this.carrito = carrito;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public CarritoProductoId getId() {
        return id;
    }

    public void setId(CarritoProductoId id) {
        this.id = id;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CarritoProducto other = (CarritoProducto) obj;
        return id.equals(other.id);
    }
}
