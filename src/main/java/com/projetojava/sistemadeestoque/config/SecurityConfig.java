package com.projetojava.sistemadeestoque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.projetojava.sistemadeestoque.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/register", "/login").permitAll() // Permitir acesso público a estas páginas
                        .requestMatchers("/products", "/formProduct", "/edit/**", "/filterProducts", "/search-result")
                        .hasRole("USUARIO_LOGADO")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login"))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // // Define AuthenticationManager only if needed (e.g., custom authentication)
    // @Bean
    // public AuthenticationManager
    // authenticationManager(AuthenticationConfiguration
    // authenticationConfiguration)
    // throws Exception {
    // return (your custom authentication logic is required here) ?
    // customAuthenticationManager()
    // : authenticationConfiguration.getAuthenticationManager();
    // }

    // // Optional: Implement custom authentication manager if needed
    // private AuthenticationManager customAuthenticationManager() throws Exception
    // {
    // // Implement your custom authentication logic here (e.g., database, LDAP)
    // // ...
    // }
}