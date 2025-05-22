package com.example.FranLucuixProyectoIntegrado.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UsuarioDTO {
     private int idUsuario;

    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(max = 30, message = "El nombre no puede exceder de 30 carácteres")
    private String nombre;

    @Email(message = "El email introducido no es válido")
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String email;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String rol;

    public UsuarioDTO() {}

    public UsuarioDTO(int idUsuario, String nombre, String email, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}


