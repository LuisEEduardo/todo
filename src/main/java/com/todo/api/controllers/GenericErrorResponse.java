package com.todo.api.controllers;

import java.time.LocalDateTime;

public record GenericErrorResponse(Boolean status, Object errors, LocalDateTime timestamp) {
}
