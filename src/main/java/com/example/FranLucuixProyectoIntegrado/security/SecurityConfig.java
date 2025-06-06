package com.example.FranLucuixProyectoIntegrado.security;

import com.example.FranLucuixProyectoIntegrado.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors().and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth -> auth
                // Productos (GET permisos públicos para ruta exacta y subrutas)
                .requestMatchers(HttpMethod.GET, "/productos").permitAll()
                .requestMatchers(HttpMethod.GET, "/productos/**").permitAll()
                // Crear, actualizar y borrar productos solo ADMIN (incluyendo ruta exacta y subrutas)
                .requestMatchers(HttpMethod.POST, "/productos").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/productos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/productos/**").hasRole("ADMIN")
                // Usuarios (registro público)
                .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuarios/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")
                        
                // Auth login, registro y logout (públicos)
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/registro").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/logout").permitAll()
                // Carritos y carrito-producto (rutas exactas y subrutas, roles CLIENTE y ADMIN)
                .requestMatchers("/carritos").hasAnyRole("CLIENTE", "ADMIN")
                .requestMatchers("/carritos/**").hasAnyRole("CLIENTE", "ADMIN")
                .requestMatchers("/carrito-producto").hasAnyRole("CLIENTE", "ADMIN")
                .requestMatchers("/carrito-producto/**").hasAnyRole("CLIENTE", "ADMIN")
                // Pedidos (rutas exactas y subrutas)
                .requestMatchers(HttpMethod.GET, "/pedidos").hasAnyRole("CLIENTE", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/pedidos").hasAnyRole("CLIENTE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/pedidos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/pedidos/**").hasRole("ADMIN")
                // Cualquier otra ruta autenticada
                .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable())
                .logout(logout -> logout
                .logoutUrl("/auth/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setStatus(HttpServletResponse.SC_OK);
                })
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
