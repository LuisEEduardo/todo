package com.todo.application.resources;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RequestUpdateTodoResource {

    @NotNull(message = "id is not null")
    private UUID id;

    @NotNull(message = "description is not null")
    @NotBlank(message = "description is not blank")
    @Size(min = 2, max = 1000, message = "description can have between 2 and 1000 characters")
    private String description;

    @NotNull(message = "status is not null")
    private Boolean status;
}
