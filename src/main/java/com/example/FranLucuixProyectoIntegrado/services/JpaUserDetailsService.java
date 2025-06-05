package com.example.FranLucuixProyectoIntegrado.services;

import com.example.FranLucuixProyectoIntegrado.entities.Usuario;
import com.example.FranLucuixProyectoIntegrado.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        return usuarioRepository.findByNombreOrEmail(input, input)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con nombre o email: " + input));
    }
}
