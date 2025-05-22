package com.example.FranLucuixProyectoIntegrado.DTOs;


public class CarritoDTO {

    private int idCarrito;
    private int idUsuario;

    public CarritoDTO() {}

    public CarritoDTO(int idCarrito, int idUsuario) {
        this.idCarrito = idCarrito;
        this.idUsuario = idUsuario;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}

