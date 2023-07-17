package com.example.crudmicroservice.chat.service;


import com.example.crudmicroservice.chat.dto.ChatMessageCreateDTO;
import com.example.crudmicroservice.chat.exception.ChatMessageNotFoundException;
import com.example.crudmicroservice.chat.model.ChatMessage;
import com.example.crudmicroservice.chat.model.Status;
import com.example.crudmicroservice.chat.repository.ChatMessageRepository;
import com.example.crudmicroservice.chat.repository.mongotemplate.ChatMessageDALImpl;
import com.example.crudmicroservice.chat.util.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageDALImpl chatMessageDALImpl;

    @Autowired
    public ChatMessageService(ChatMessageRepository chatMessageRepository, ChatMessageDALImpl chatMessageDALImpl) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatMessageDALImpl = chatMessageDALImpl;
    }

    public ChatMessage save(ChatMessageCreateDTO message) {
        ChatMessage chatMessage = convertDTOToEntity(message);
        // TODO: дописать проверки
        return chatMessageRepository.save(chatMessage);
//                .orElseThrow(() -> new ChatMessageCreateException(chatMessage.toString()));
    }

    private ChatMessage convertDTOToEntity(ChatMessageCreateDTO message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setChatRoomId(message.getChatRoomId());
        chatMessage.setSenderId(message.getSenderId());
        chatMessage.setContent(message.getContent());
        chatMessage.setTimestamp(Timestamp.get());
        chatMessage.setStatus(Status.DELIVERED);
        return chatMessage;
    }


    public List<ChatMessage> getAllChatMessage() {
        return chatMessageRepository.findAll();
    }

    public void deleteChatMessage(ChatMessage message) {
        chatMessageRepository.delete(message);
    }
    public ChatMessage getChatMessageByMessageId(String messageId) throws ChatMessageNotFoundException {
        return chatMessageRepository.findChatMessageByMessageId(messageId)
                .orElseThrow(() ->
                        new ChatMessageNotFoundException("Chat message with id " + messageId + " not found"));
    }
}
