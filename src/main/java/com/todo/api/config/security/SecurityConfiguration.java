package com.todo.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/v1/user/authenticate", //url que usaremos para fazer login
            "/v1/user", //url que usaremos para criar um usuário
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/index.css",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/swagger-initializer.js",
            "/swagger-ui/favicon-32x32.png",
            "/swagger-ui/favicon-16x16.png",
            "/v3/api-docs/swagger-config",
            "/v3/api-docs"
    };

    // Endpoints que requerem autenticação para serem acessados
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
//            "/v1/todo",
            "/v1/todo/**"
    };

//    // Endpoints que só podem ser acessador por usuários com permissão de cliente
//    public static final String [] ENDPOINTS_CUSTOMER = {
//            "/todo"
//    };
//
//    // Endpoints que só podem ser acessador por usuários com permissão de administrador
//    public static final String [] ENDPOINTS_ADMIN = {
//            "/todo/**"
//    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Desativa a proteção contra CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura a política de criação de sessão como stateless
                        .authorizeHttpRequests(authorize -> authorize.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll() // Habilita a autorização para as requisições HTTP
                                .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                                .anyRequest().denyAll()
                                // Adiciona o filtro de autenticação de usuário que criamos, antes do filtro de segurança padrão do Spring Security
                                ).addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
