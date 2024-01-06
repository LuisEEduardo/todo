package com.todo.application.mapper;

import com.todo.application.resources.ResponseTodoResource;
import com.todo.business.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TodoMapper.class})
public interface TodoMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "description", source = "description")
    ResponseTodoResource todoToResponseTodoResource(Todo todo);

    Iterable<ResponseTodoResource> todosToResponseTodoResources(Iterable<Todo> todos);

}
