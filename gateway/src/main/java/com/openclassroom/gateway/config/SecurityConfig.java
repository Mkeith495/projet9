package com.openclassroom.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
            .csrf(csrf -> csrf.disable()) 
            
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/login", "/logout", "/css/**", "/js/**").permitAll()
                
                .anyExchange().authenticated()
            )
            
            .formLogin(Customizer.withDefaults())

            .httpBasic(Customizer.withDefaults())
            
            .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        @SuppressWarnings("deprecation")
        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("docteur_admin")
            .password("mediscreen2026")
            .roles("ADMIN")
            .build();
        return new MapReactiveUserDetailsService(admin);
    }
}