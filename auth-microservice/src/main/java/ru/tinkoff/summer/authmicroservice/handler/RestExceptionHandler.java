package ru.tinkoff.summer.authmicroservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tinkoff.summer.authmicroservice.exception.UserAlreadyExistsException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {
    protected Map<String, Object> getBody(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("message", ex.getMessage());
        return body;
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    protected ResponseEntity<Object> handleIllegalArgument(BadCredentialsException ex) {
        return new ResponseEntity(getBody(ex), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ResponseEntity<Object> alreadyExists(UserAlreadyExistsException exception) {
        return new ResponseEntity(getBody(exception), HttpStatus.CONFLICT);
    }
}
