package com.todo.api.controllers.genericResponses;

import java.time.LocalDateTime;

public record ErrorResponse(Boolean status, Object errors, LocalDateTime timestamp) {
}
