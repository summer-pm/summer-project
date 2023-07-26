package com.example.crudmicroservice.chat.service;


import com.example.crudmicroservice.chat.dto.ChatUserCreateDTO;
import com.example.crudmicroservice.chat.model.ChatRoom;
import com.example.crudmicroservice.chat.model.ChatUser;
import com.example.crudmicroservice.chat.repository.ChatRoomRepository;
import com.example.crudmicroservice.chat.repository.ChatUserRepository;
import com.example.crudmicroservice.chat.repository.mongotemplate.ChatUserDALImpl;
import com.example.crudmicroservice.user.model.User;
import com.example.crudmicroservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatUserService {

    private final ChatUserRepository chatUserRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;
    private final ChatUserDALImpl chatUserDAL;

    @Autowired
    public ChatUserService(ChatUserRepository chatUserRepository, ChatRoomRepository chatRoomRepository, UserService userService, ChatUserDALImpl chatUserDAL) {
        this.chatUserRepository = chatUserRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.userService = userService;
        this.chatUserDAL = chatUserDAL;
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
        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(roomId).orElse(null);
        if (chatRoom != null) {
            String interlocutorID = chatRoom.getUsers().stream()
                    .filter(userIID -> !userId.equals(userIID)).findFirst().orElse("-1");
            ChatUser chatUser = chatUserRepository.findChatUserById(interlocutorID);
            User user = userService.getUserById(Long.parseLong(chatUser.getUserPgId()));
            return user.getUsername();
        }
        else {
            return "-1";
        }
    }

    public List<ChatUser> getAllChatUser() {
        return chatUserRepository.findAll();
    }

    public ChatUser deleteChatUser(String id) {
        return chatUserRepository.deleteChatUserById(id);
    }

    protected void updateRoomInfo(String roomId, List<String> users) {
        users.forEach(userId -> {
            chatUserDAL.updateUsersRoom(userId, roomId);
        });
    }

    public void deleteRoomFromUser(String userId, String roomId) {
        chatUserDAL.deleteRoomFromUser(userId, roomId);
    }
}
