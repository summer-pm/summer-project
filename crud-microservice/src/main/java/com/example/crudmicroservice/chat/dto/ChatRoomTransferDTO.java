package com.example.crudmicroservice.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomTransferDTO {
    private String roomId;
    private String interlocutor;
    private String lastMessage;
}
