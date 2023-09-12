package com.ilkinmehdiyev.unitech_test_task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    ProblemDetail handleUserNotFoundException(UserNotFoundException e) {
        return asProblemDetail(e.getMessage(), HttpStatus.NOT_FOUND, "User Not Found", "errors/not-found", "Generic");
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    ProblemDetail handleUserUnauthorizedException(UserUnauthorizedException e) {
        return asProblemDetail(e.getMessage(), HttpStatus.UNAUTHORIZED, "User Not Authorized",
                "errors/not-authorized", "Generic");
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    ProblemDetail handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return asProblemDetail(e.getMessage(), HttpStatus.BAD_REQUEST, "User Already Exists",
                "errors/bad-request", "Generic");
    }

    private ProblemDetail asProblemDetail(String message, HttpStatus httpStatus, String title, String uri, String errorCategory) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);
        problemDetail.setTitle(title);
        problemDetail.setType(URI.create(uri));
        problemDetail.setProperty("errorCategory", errorCategory);
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
