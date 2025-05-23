package com.example.FranLucuixProyectoIntegrado.security;

import com.example.FranLucuixProyectoIntegrado.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private JpaUserDetailsService userDetailsService;
    
//    
//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    return http
//        .csrf(csrf -> csrf.disable())
//        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        .authorizeHttpRequests(auth -> auth
//            .requestMatchers(HttpMethod.GET, "/productos").permitAll()
//            .requestMatchers(HttpMethod.POST,"/usuarios/**").permitAll()
//
//            .requestMatchers(HttpMethod.POST, "/productos").hasRole("ADMIN")
//            .requestMatchers(HttpMethod.PUT, "/productos/**").hasRole("ADMIN")
//            .requestMatchers(HttpMethod.DELETE, "/productos/**").hasRole("ADMIN")
//
//            .requestMatchers("/carritos/**").hasAnyRole("CLIENTE", "ADMIN")
//            .requestMatchers("/carrito-producto/**").hasAnyRole("CLIENTE", "ADMIN")
//
//            .requestMatchers(HttpMethod.GET, "/pedidos").hasAnyRole("CLIENTE", "ADMIN")
//            .requestMatchers(HttpMethod.POST, "/pedidos").hasAnyRole("CLIENTE", "ADMIN")
//
//            // Pedidos: solo admin puede modificar o eliminar
//            .requestMatchers(HttpMethod.PUT, "/pedidos/**").hasRole("ADMIN")
//            .requestMatchers(HttpMethod.DELETE, "/pedidos/**").hasRole("ADMIN")
//
//
//            // Todo lo demás: autenticación obligatoria
//            .anyRequest().authenticated()
//        )
//        .httpBasic(withDefaults())
//        .build();
//}


//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//            .userDetailsService(userDetailsService)
//            .passwordEncoder(passwordEncoder())
//            .and()
//            .build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        
        return new BCryptPasswordEncoder();
    }
//    
    
     @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(withDefaults()) 
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );
        return http.build();
    }
    
}
