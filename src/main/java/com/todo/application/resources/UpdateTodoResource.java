package com.todo.application.resources;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateTodoResource {

    @NotNull(message = "id is not null")
    @NotBlank(message = "id is not blank")
    private UUID id;

    @NotNull(message = "description is not null")
    @NotBlank(message = "description is not blank")
    @Max(value = 1000, message = "description have max of 1000 characters")
    private String description;

    @NotNull(message = "status is not null")
    @NotBlank(message = "status is not blank")
    private Boolean status;
}
