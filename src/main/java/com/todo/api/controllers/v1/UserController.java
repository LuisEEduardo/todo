package com.todo.api.controllers.v1;

import com.todo.api.controllers.MainController;
import com.todo.application.interfaces.UserService;
import com.todo.application.resources.RequestCreateUserResource;
import com.todo.application.resources.RequestLoginUserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/user", produces = {"application/json"})
@Tag(name = "User")
public class UserController extends MainController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "authenticate user", method = "POST")
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid RequestLoginUserResource loginUserResource) {
        return CustomResponse(service.autheticateUser(loginUserResource));
    }

    @Operation(summary = "create user", method = "POST")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid RequestCreateUserResource requestCreateUserResource) {
        service.createUser(requestCreateUserResource);
        return CustomResponse("create");
    }


}
