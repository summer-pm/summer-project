package ru.tinkoff.summer.authmicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class BadCredentialsException extends Exception{
    public BadCredentialsException(String message) {
        super(message);
    }
}
