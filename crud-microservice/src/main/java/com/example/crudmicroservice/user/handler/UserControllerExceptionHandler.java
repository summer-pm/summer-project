package com.example.crudmicroservice.user.handler;

import com.example.crudmicroservice.user.exception.UserAlreadyExistsException;
import com.example.crudmicroservice.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
class UserControllerExceptionHandler {
    protected Map<String, Object> getBody(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("message", ex.getMessage());
        return body;
    }



    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> elementNotFound(UserNotFoundException exception) {
        return new ResponseEntity(getBody(exception), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ResponseEntity<Object> alreadyExists(UserAlreadyExistsException exception) {
        return new ResponseEntity(getBody(exception), HttpStatus.CONFLICT);
    }
}
