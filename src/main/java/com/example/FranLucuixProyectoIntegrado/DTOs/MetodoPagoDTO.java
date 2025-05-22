/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.DTOs;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author francis
 */
public class MetodoPagoDTO {

    private int idPago;

    @NotBlank(message = "El tipo de método de pago no puede estar vacío")
    private String tipo;

    public MetodoPagoDTO() {}

    public MetodoPagoDTO(int idPago, String tipo) {
        this.idPago = idPago;
        this.tipo = tipo;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

