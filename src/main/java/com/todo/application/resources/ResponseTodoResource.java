package com.todo.application.resources;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ResponseTodoResource {
    private UUID id;
    private Boolean status;
    private String description;
}
