package com.example.crudmicroservice.chat.exception;

public class ChatMessageCreateException extends RuntimeException {
    public ChatMessageCreateException(String message) {
        super(message);
    }

    public ChatMessageCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
