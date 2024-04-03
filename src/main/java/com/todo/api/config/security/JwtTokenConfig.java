package com.todo.api.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.todo.application.impl.UserDetailsImpl;
import com.todo.utils.TimezoneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Service
public class JwtTokenConfig {

    private final String SECRET_KEY; // Chave secreta utilizada para gerar e verificar o token
    private final String ISSUER; // Emissor do token


    public JwtTokenConfig(@Value(value = "${api.security.token.secret-key}") String SECRET_KEY,
                          @Value(value = "${api.security.token.issuer}") String ISSUER) {
        this.SECRET_KEY = SECRET_KEY;
        this.ISSUER = ISSUER;
    }

    public String generateToken(UserDetailsImpl userDatails) throws  JWTCreationException{
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            return JWT.create()
                    .withIssuer(ISSUER) // define o emissor do token
                    .withIssuedAt(creationDate()) // data da emissão do token
                    .withExpiresAt(expiretionDate()) // expiração do token
                    .withSubject(userDatails.getUsername()) // assunto do token (neste caso o token)
                    .sign(algorithm); // Assina o token usando o algoritmo especificado
        } catch (JWTCreationException exception) {
            log.error("Error to generate token, error message: {}", exception.getMessage());
            throw new JWTCreationException("Error to generate token", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            // Define o algoritmo HMAC256para verificar a assinatura do token passando a chave secreta definida
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            return JWT.require(algorithm)
                    .withIssuer(ISSUER) // define o emissor
                    .build()
                    .verify(token) // verifica a validade do token
                    .getSubject(); // obtém o assunto (neste caso, o nome de usuário) do token
        } catch (JWTVerificationException exception) {
            log.error("Error to verify token, error message: {}", exception.getMessage());
            throw new JWTVerificationException("token invalid or expired");
        }
    }

    public Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of(TimezoneUtils.getTimezone())).toInstant();
    }

    public Instant expiretionDate() {
        return ZonedDateTime.now(ZoneId.of(TimezoneUtils.getTimezone())).plusHours(4).toInstant();
    }

}
