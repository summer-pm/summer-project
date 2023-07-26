package com.example.crudmicroservice.chat.dto;

import com.example.crudmicroservice.chat.model.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomTransferDTO {
    private String roomId;
    private String interlocutor;
    private ChatMessage lastMessage;
}
