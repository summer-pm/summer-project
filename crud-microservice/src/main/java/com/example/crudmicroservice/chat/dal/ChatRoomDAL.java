package com.example.crudmicroservice.chat.dal;

import com.example.crudmicroservice.chat.model.ChatRoom;

import java.util.List;

public interface ChatRoomDAL {
    ChatRoom findChatRoomByUsersContaining(List<String> users);
}
