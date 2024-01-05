package com.todo.application.resources;

import java.util.UUID;

public record ResponseTodoResource(UUID id, Boolean status, String description) {
}
