package com.example.FranLucuixProyectoIntegrado.DTOs;

import jakarta.validation.constraints.Min;



public class CarritoProductoDTO {

    private int idCarrito;
    private int idProducto;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

    public CarritoProductoDTO() {}

    public CarritoProductoDTO(int idCarrito, int idProducto, int cantidad) {
        this.idCarrito = idCarrito;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}



