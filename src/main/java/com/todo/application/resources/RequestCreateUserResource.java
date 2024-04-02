package com.todo.application.resources;

import com.todo.business.UserRole;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestCreateUserResource(
        @NotNull(message = "login is not null")
        @NotBlank(message = "login is not blank")
        @Size(max = 150, message = "login have max of 150 characters")
        String login,

        @NotNull(message = "password is not null")
        @NotBlank(message = "password is not blank")
        @Size(max = 150, message = "password have max of 150 characters")
        String password,

        @NotNull(message = "role is not null")
        UserRole role) {
}
