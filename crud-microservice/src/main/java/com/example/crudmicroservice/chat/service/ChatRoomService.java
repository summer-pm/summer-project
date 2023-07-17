package com.example.crudmicroservice.chat.service;


import com.example.crudmicroservice.chat.dto.ChatRoomCreateDTO;
import com.example.crudmicroservice.chat.exception.ChatRoomCreateException;
import com.example.crudmicroservice.chat.exception.ChatRoomNotFoundException;
import com.example.crudmicroservice.chat.model.ChatRoom;
import com.example.crudmicroservice.chat.repository.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomService (ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public ChatRoom save(ChatRoomCreateDTO chatRoomCreateDTO) throws RuntimeException {
        ChatRoom chatRoom = convertDTOToEntity(chatRoomCreateDTO);
        int usersCount = chatRoom.getUsers().size();
        if (usersCount != 2) {
            log.error("Can't create ChatRoom. Required numbers of users 2. Actual: {}.", usersCount);
            throw new ChatRoomCreateException("The count of users in the ChatRoom isn't equal to 2.");
        }
        else {
            return chatRoomRepository.save(chatRoom);
                    // TODO: дописать исключение
//                    .orElseThrow(() -> new RuntimeException("Saving aborted. Something went wrong"));
        }

    }

    private ChatRoom convertDTOToEntity(ChatRoomCreateDTO chatRoomCreateDTO) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setUsers(chatRoomCreateDTO.getUsers());
        return chatRoom;
    }

    public ChatRoom getChatRoomByRoomId(String id) throws ChatRoomNotFoundException {
        return chatRoomRepository.findChatRoomById(id)
                .orElseThrow(() ->
                        new ChatRoomNotFoundException("Chat room with id " + id + "not found."));
    }

    public List<ChatRoom> getAllChatRoom() {
        return chatRoomRepository.findAll();
    }
}
