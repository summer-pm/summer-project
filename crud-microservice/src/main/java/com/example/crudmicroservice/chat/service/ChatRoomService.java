package com.example.crudmicroservice.chat.service;


import com.example.crudmicroservice.chat.dto.ChatRoomCreateDTO;
import com.example.crudmicroservice.chat.dto.ChatRoomTransferDTO;
import com.example.crudmicroservice.chat.exception.ChatRoomCreateException;
import com.example.crudmicroservice.chat.exception.ChatRoomNotFoundException;
import com.example.crudmicroservice.chat.model.ChatMessage;
import com.example.crudmicroservice.chat.model.ChatRoom;
import com.example.crudmicroservice.chat.model.ChatUser;
import com.example.crudmicroservice.chat.repository.ChatMessageRepository;
import com.example.crudmicroservice.chat.repository.ChatRoomRepository;
import com.example.crudmicroservice.chat.repository.ChatUserRepository;
import com.example.crudmicroservice.chat.repository.mongotemplate.ChatRoomDALImpl;
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
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomDALImpl chatRoomDAL;

    @Autowired
    public ChatRoomService (ChatRoomRepository chatRoomRepository, ChatUserRepository chatUserRepository, ChatMessageRepository chatMessageRepository, ChatRoomDALImpl chatRoomDAL) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatUserRepository = chatUserRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomDAL = chatRoomDAL;
    }

    public ChatRoom getChatRoomByUsers(String firstUserId, String secondUserId) {
        List<String> usersId = Arrays.asList(firstUserId, secondUserId);
        return chatRoomRepository.findChatRoomByUsersContains(usersId)
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
            if (chatRoomAlreadyExist(chatRoomCreateDTO))
                throw new ChatRoomCreateException("The chat room already exists");
            else {
                ChatRoom room = chatRoomRepository.save(chatRoom);
                chatUserService.updateRoomInfo(room.getId(), room.getUsers());
                return room;
            }

        }

    }

    private boolean chatRoomAlreadyExist(ChatRoomCreateDTO chatRoomCreateDTO) {

        ChatRoom chatRoom = chatRoomDAL
                .findChatRoomByUsersContaining(chatRoomCreateDTO.getUsers());

        return chatRoom != null;
    }

    public List<ChatRoomTransferDTO> getChatRoomsByUserId(String userId) {
        ChatUser user = chatUserRepository.findChatUserById(userId);
        List<ChatRoomTransferDTO> transferDTOs = new ArrayList<>();

        user.getRooms().forEach(roomId -> {
            ChatRoomTransferDTO transferDTO = new ChatRoomTransferDTO();
            transferDTO.setRoomId(roomId);
            String interlocutorName = chatUserService.getInterlocutorNameByRoomIdAndUserId(roomId, userId);
            transferDTO.setInterlocutor(interlocutorName);
            ChatMessage lastMessage = chatMessageRepository.findTopByChatRoomIdOrderByTimestampDesc(roomId);
            transferDTO.setLastMessage(lastMessage);
            transferDTOs.add(transferDTO);
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

    public void deleteRoom(String roomId) {
        ChatRoom chatRoom = chatRoomRepository
                .findById(roomId)
                .orElseThrow(() ->
                        new ChatRoomNotFoundException("Chat Room was not found"));
        chatRoom.getUsers().forEach(userId -> {
            chatUserService.deleteRoomFromUser(userId, roomId);
        });
        chatRoomRepository.delete(chatRoom);
    }
}
