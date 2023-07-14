package com.example.crudmicroservice.chat.exception;

public class ChatUserCreateException extends RuntimeException {
    public ChatUserCreateException(String message) {
        super(message);
    }

    public ChatUserCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
