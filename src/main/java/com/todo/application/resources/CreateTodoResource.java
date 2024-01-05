package com.todo.application.resources;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTodoResource {

    @NotNull(message = "description is not null")
    @NotBlank(message = "description is not blank")
    @Max(value = 1000, message = "description have max of 1000 characters")
    private String description;
}
