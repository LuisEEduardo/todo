package com.todo.application.interfaces;

import com.todo.application.resources.RequestCreateTodoResource;
import com.todo.application.resources.ResponseTodoResource;
import com.todo.application.resources.RequestUpdateTodoResource;

import java.util.UUID;

public interface TodoService {
    Iterable<ResponseTodoResource> findAll();
    ResponseTodoResource findById(UUID id);
    ResponseTodoResource create(RequestCreateTodoResource todo);
    ResponseTodoResource update(RequestUpdateTodoResource todo);
    Boolean delete(UUID id);
}
