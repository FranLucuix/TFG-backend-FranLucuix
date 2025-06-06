package com.example.FranLucuixProyectoIntegrado.controllers;

import com.example.FranLucuixProyectoIntegrado.entities.Carrito;
import com.example.FranLucuixProyectoIntegrado.entities.Usuario;
import com.example.FranLucuixProyectoIntegrado.repositories.ICarritoRepository;
import com.example.FranLucuixProyectoIntegrado.repositories.IUsuarioRepository;
import com.example.FranLucuixProyectoIntegrado.services.DTOConverter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DTOConverter conversorDTO;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ICarritoRepository carritoRepository;

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        SecurityContextHolder.clearContext();

        ResponseCookie cookie = ResponseCookie.from("JSESSIONID", "")
                .path("/")
                .httpOnly(true)
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok("Sesión cerrada");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.username(), loginRequest.password()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

            Usuario usuario = (Usuario) authentication.getPrincipal();

            return ResponseEntity.ok(new LoginResponse(
                    usuario.getIdUsuario(),
                    usuario.getNombre(),
                    usuario.getEmail(),
                    usuario.getRol()
            ));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody Usuario usuario) {
        if (usuarioRepository.findByNombre(usuario.getNombre()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nombre de usuario ya está en uso");
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ya está registrado");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol("CLIENTE");

        Carrito carrito = new Carrito();

        usuario.setCarrito(carrito);
        carrito.setUsuario(usuario);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return ResponseEntity.ok(conversorDTO.usuarioToDTO(usuarioGuardado));
    }

    public record LoginRequest(String username, String password) {

    }

    public record LoginResponse(int idUsuario, String nombre, String email, String rol) {

    }
}
