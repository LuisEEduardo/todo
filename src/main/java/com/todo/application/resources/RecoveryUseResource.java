package com.todo.application.resources;

import com.todo.business.UserRole;

import java.util.UUID;

public record RecoveryUseResource(UUID id, String email, UserRole role) {
}
