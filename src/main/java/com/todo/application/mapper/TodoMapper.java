package com.todo.application.mapper;

import com.todo.application.resources.CreateTodoResource;
import com.todo.application.resources.ResponseTodoResource;
import com.todo.application.resources.UpdateTodoResource;
import com.todo.business.Todo;
import org.mapstruct.Mapper;

//@org.mapstruct.Mapper(componentModel = "spring")
@Mapper(componentModel = "spring")
public interface TodoMapper {

    ResponseTodoResource todoToResponseTodoResource(Todo todo);

    Iterable<ResponseTodoResource> todosToResponseTodoResources(Iterable<Todo> todos);

    Todo responseTodoResourceToTodo(ResponseTodoResource responseTodoResource);

    Todo createTodoResourceToTodo(CreateTodoResource createTodoResource);

    Todo updateTodoResourceToTodo(UpdateTodoResource updateTodoResource);
}
