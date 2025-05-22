/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FranLucuixProyectoIntegrado.services;

import com.example.FranLucuixProyectoIntegrado.DTOs.UsuarioDTO;
import com.example.FranLucuixProyectoIntegrado.entities.Usuario;
import com.example.FranLucuixProyectoIntegrado.repositories.IUsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author francis
 */
@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    @Autowired
    private DTOConverter dtoConverter;

    public List<UsuarioDTO> findAll() {
        
        List<Usuario> usuarios=usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO=new ArrayList<>();
        for(Usuario u:usuarios){
            usuariosDTO.add(dtoConverter.usuarioToDTO(u));
        }
        return usuariosDTO;
    }

    public UsuarioDTO findById(int id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return dtoConverter.usuarioToDTO(usuario);
    }

    @Transactional
    public UsuarioDTO createUsuario(UsuarioDTO dto) {
        Usuario usuario = dtoConverter.usuarioToEntity(dto);
        return dtoConverter.usuarioToDTO(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioDTO updateUsuario(int id, UsuarioDTO dto) {
        Usuario existing = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existing.setNombre(dto.getNombre());
        existing.setEmail(dto.getEmail());
        existing.setRol(dto.getRol());
        // No actualizamos el ID ni el carrito o pedidos desde aquí

        return dtoConverter.usuarioToDTO(usuarioRepository.save(existing));
    }

    @Transactional
    public void deleteUsuario(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}

