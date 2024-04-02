package com.todo.application.interfaces;

import com.todo.application.resources.RequestCreateUserResource;
import com.todo.application.resources.RequestLoginUserResource;
import com.todo.application.resources.ResponseRecoveryJwtTokenResource;

public interface UserService {
    ResponseRecoveryJwtTokenResource autheticateUser(RequestLoginUserResource loginUserResource);
    void createUser(RequestCreateUserResource requestCreateUserResource);
}
