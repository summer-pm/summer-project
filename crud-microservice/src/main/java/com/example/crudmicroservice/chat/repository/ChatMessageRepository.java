package com.example.crudmicroservice.chat.repository;


import com.example.crudmicroservice.chat.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findChatMessageBySenderId(String senderName);
    Optional<ChatMessage> findChatMessageByMessageId(String messageId);
    ChatMessage save(ChatMessage chatMessage);
}
