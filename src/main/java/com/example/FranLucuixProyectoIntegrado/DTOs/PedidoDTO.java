/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 *
 * @author francis
 */
public class PedidoDTO {

    private int idPedido;
    private int idUsuario;
    private int idPago;

    private LocalDate fechaPedido;

    @DecimalMin(value = "0.0", message = "El total debe ser mayor o igual a 0")
    private double total;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    private LocalDate fechaEntrega;

    public PedidoDTO() {
    }

    public PedidoDTO(int idPedido, int idUsuario, int idPago, LocalDate fechaPedido, double total, String estado, String direccion, LocalDate fechaEntrega) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.idPago = idPago;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.estado = estado;
        this.direccion = direccion;
        this.fechaEntrega = fechaEntrega;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}
