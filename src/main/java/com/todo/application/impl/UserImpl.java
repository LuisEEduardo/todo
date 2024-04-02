package com.todo.application.impl;

import com.todo.api.config.security.JwtTokenConfig;
import com.todo.api.config.security.SecurityConfiguration;
import com.todo.application.interfaces.UserService;
import com.todo.application.resources.RequestCreateUserResource;
import com.todo.application.resources.RequestLoginUserResource;
import com.todo.application.resources.ResponseRecoveryJwtTokenResource;
import com.todo.business.User;
import com.todo.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenConfig jwtTokenConfig;
    private final UserRepository repository;
    private final SecurityConfiguration securityConfiguration;

    public UserImpl(AuthenticationManager authenticationManager, JwtTokenConfig jwtTokenConfig, UserRepository repository, SecurityConfiguration securityConfiguration) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenConfig = jwtTokenConfig;
        this.repository = repository;
        this.securityConfiguration = securityConfiguration;
    }

    // Método responsável por autenticar um usuário e retornar um token JWT
    @Override
    public ResponseRecoveryJwtTokenResource autheticateUser(RequestLoginUserResource loginUserResource) {

        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken userAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserResource.login(),
                loginUserResource.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(userAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        var token = jwtTokenConfig.generateToken(userDetails);

        return new ResponseRecoveryJwtTokenResource(token);
    }

    // Método responsável por criar um usuário
    @Override
    public void createUser(RequestCreateUserResource requestCreateUserResource) {
        // Cria um novo usuário com os dados fornecidos
        User user = new User(requestCreateUserResource.login(),
                securityConfiguration.passwordEncoder().encode(requestCreateUserResource.password()),
                requestCreateUserResource.role());

        // Salva o novo usuário no banco de dados
        repository.save(user);
    }
}
