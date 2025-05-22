/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author francis
 */
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    private String nombre;
    private double precio;
    private double precioRebaja;
    private int stock;
    private String descripcion;
    private String categoria;

    @OneToMany(mappedBy = "producto")
    private List<DetallePedido> detallesPedido;

    @OneToMany(mappedBy = "producto")
    private List<CarritoProducto> carritoProductos;

    public Producto(int idProducto, String nombre, double precio, double precioRebaja, int stock, String descripcion, String categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.precioRebaja = precioRebaja;
        this.stock = stock;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public Producto(String nombre, double precio, double precioRebaja, int stock, String descripcion, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.precioRebaja = precioRebaja;
        this.stock = stock;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public Producto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Producto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioRebaja() {
        return precioRebaja;
    }

    public void setPrecioRebaja(double precioRebaja) {
        this.precioRebaja = precioRebaja;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idProducto;
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
        final Producto other = (Producto) obj;
        return this.idProducto == other.idProducto;
    }

}
