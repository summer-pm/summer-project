package com.example.crudmicroservice.chat.service;


import com.example.crudmicroservice.chat.dto.ChatUserCreateDTO;
import com.example.crudmicroservice.chat.model.ChatUser;
import com.example.crudmicroservice.chat.repository.ChatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatUserService {

    private final ChatUserRepository chatUserRepository;

    @Autowired
    public ChatUserService(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    public ChatUser getChatUserByPgId(String id) {
        // TODO: дописать проверки
        return chatUserRepository.findChatUserByUserPgId(id);
    }


    public ChatUser saveChatUser(ChatUserCreateDTO chatUserCreateDTO) {
        // TODO: дописать проверки
        ChatUser chatUser = convertDTOToEntity(chatUserCreateDTO);
        return chatUserRepository.save(chatUser);
    }

    private ChatUser convertDTOToEntity(ChatUserCreateDTO chatUserCreateDTO) {
        ChatUser chatUser = new ChatUser();
        chatUser.setUserPgId(chatUserCreateDTO.getUserPgId());
        chatUser.setRooms(chatUserCreateDTO.getRooms());
        return chatUser;
    }

    public String getInterlocutorNameByRoomIdAndUserId(String roomId, String userId) {
        return null;
    }

    public List<ChatUser> getAllChatUser() {
        return chatUserRepository.findAll();
    }

    public ChatUser deleteChatUser(String id) {
        return chatUserRepository.deleteChatUserById(id);
    }
}
