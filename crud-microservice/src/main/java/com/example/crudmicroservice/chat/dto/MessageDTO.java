package com.example.crudmicroservice.chat.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MessageDTO {
    private String id;
    private String recipient;
    private String sender;
    private String content;

    public MessageDTO(String id, String sender, String content) {
        this.id = id;
        this.sender = sender;
        this.content = content;
    }
}
