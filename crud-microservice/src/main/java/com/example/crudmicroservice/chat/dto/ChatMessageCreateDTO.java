package com.example.crudmicroservice.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageCreateDTO {
    private String chatRoomId;
    private String senderId;
    private String content;
}
