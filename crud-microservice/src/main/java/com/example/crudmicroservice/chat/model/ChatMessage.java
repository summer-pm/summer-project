package com.example.crudmicroservice.chat.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "messages")
public class ChatMessage {
    @Id
    private String messageId;
    private String chatRoomId;
    private String senderId;
    private String content;
    private LocalDateTime timestamp;
    private Status status;
}
