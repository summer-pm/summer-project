package com.example.crudmicroservice.chat.service;


import com.example.crudmicroservice.chat.dto.ChatRoomCreateDTO;
import com.example.crudmicroservice.chat.dto.ChatRoomTransferDTO;
import com.example.crudmicroservice.chat.exception.ChatRoomCreateException;
import com.example.crudmicroservice.chat.exception.ChatRoomNotFoundException;
import com.example.crudmicroservice.chat.model.ChatRoom;
import com.example.crudmicroservice.chat.model.ChatUser;
import com.example.crudmicroservice.chat.repository.ChatRoomRepository;
import com.example.crudmicroservice.chat.repository.ChatUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ChatRoomService {

    @Autowired
    private ChatUserService chatUserService;

    private final ChatRoomRepository chatRoomRepository;
    private final ChatUserRepository chatUserRepository;

    @Autowired
    public ChatRoomService (ChatRoomRepository chatRoomRepository, ChatUserRepository chatUserRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatUserRepository = chatUserRepository;
    }

    public ChatRoom getChatRoomByUsers(String firstUserId, String secondUserId) {
        List<String> usersId = Arrays.asList(firstUserId, secondUserId);
        return chatRoomRepository.findChatRoomByUsersContaining(usersId)
                .orElseThrow(() ->
                     new ChatRoomNotFoundException("Chat room with such users was not found.")
                );
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

    public List<ChatRoomTransferDTO> getChatRoomsByUserId(String userId) {
        ChatUser user = chatUserRepository.findChatUserById(userId);
        List<ChatRoomTransferDTO> transferDTOs = new ArrayList<>();

        user.getRooms().forEach(roomId -> {
            ChatRoomTransferDTO transferDTO = new ChatRoomTransferDTO();
            transferDTO.setRoomId(roomId);
            String interlocutorName = chatUserService.getInterlocutorNameByRoomIdAndUserId(roomId, userId);
//            transferDTO.set
        });




        return transferDTOs;
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
