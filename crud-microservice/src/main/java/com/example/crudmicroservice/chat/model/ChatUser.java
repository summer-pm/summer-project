package com.example.crudmicroservice.chat.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "users")
public class ChatUser {

    @Id
    private String id;
    private String userPgId;
    private List<String> rooms;
}
