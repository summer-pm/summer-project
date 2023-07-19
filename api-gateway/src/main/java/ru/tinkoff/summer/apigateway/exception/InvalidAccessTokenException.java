package ru.tinkoff.summer.apigateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Invalid Access Token")
public class InvalidAccessTokenException extends RuntimeException {
    public InvalidAccessTokenException(String message) {
        super(message);
    }
}
