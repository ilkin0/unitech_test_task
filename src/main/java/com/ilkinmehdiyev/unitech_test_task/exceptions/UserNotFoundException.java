package com.ilkinmehdiyev.unitech_test_task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponseException;

public class UserNotFoundException extends ErrorResponseException {
    public UserNotFoundException(HttpStatusCode status) {
        super(status);
    }

    public UserNotFoundException(Long userId){
        super(HttpStatus.NOT_FOUND, asProblemDetail(""));
    }
}
