package com.example.crudmicroservice.chat.repository;


import com.example.crudmicroservice.chat.model.ChatUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserRepository extends MongoRepository<ChatUser, String> {
    ChatUser findChatUserByUserPgId(String userId);
    ChatUser deleteChatUserById(String userId);
}
