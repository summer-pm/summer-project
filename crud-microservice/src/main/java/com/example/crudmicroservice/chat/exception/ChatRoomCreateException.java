package com.example.crudmicroservice.chat.exception;

public class ChatRoomCreateException extends RuntimeException {
    public ChatRoomCreateException(String message) {
        super(message);
    }

    public ChatRoomCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
