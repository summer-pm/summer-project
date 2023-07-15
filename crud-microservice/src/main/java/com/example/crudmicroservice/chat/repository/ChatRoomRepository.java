package com.example.crudmicroservice.chat.repository;


import com.example.crudmicroservice.chat.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findChatRoomById(String roomId);
    List<ChatRoom> findAll();
    ChatRoom save(ChatRoom chatRoom);
}
