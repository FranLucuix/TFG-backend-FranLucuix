/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author francis
 */
public class ProductoDTO {

    private int idProducto;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100)
    private String nombre;

    @DecimalMin(value = "0.0",message = "No puede ser menor que 0")
    private double precio;

    @DecimalMin(value = "0.0",message = "No puede ser menor que 0")
    private double precioRebajado;

    @Min(value = 0,message = "No puede ser menor que 0")
    private int stock;

    private String descripcion;

    @NotBlank(message = "La categoría no puede estar vacía")
    private String categoria;
    
    private String imagenUrl;

    public ProductoDTO() {}

    public ProductoDTO(int idProducto, String nombre, double precio, double precioRebajado, int stock, String descripcion, String categoria,String imagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.precioRebajado = precioRebajado;
        this.stock = stock;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.imagenUrl=imagen;
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

    public double getPrecioRebajado() {
        return precioRebajado;
    }

    public void setPrecioRebajado(double precioRebajado) {
        this.precioRebajado = precioRebajado;
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

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
    
}
