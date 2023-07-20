package ru.tinkoff.summer.apigateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Missing authorization header")
public class MissingAccessTokenException extends RuntimeException {
    public MissingAccessTokenException(String message) {
        super(message);
    }
}
