package com.example.crudmicroservice.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatUserCreateDTO {
    private String userPgId;
    private List<String> rooms;
}
