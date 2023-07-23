package com.example.crudmicroservice.chat.controller;


import com.example.crudmicroservice.chat.dto.ChatMessageCreateDTO;
import com.example.crudmicroservice.chat.exception.ChatMessageCreateException;
import com.example.crudmicroservice.chat.exception.ChatMessageNotFoundException;
import com.example.crudmicroservice.chat.model.ChatMessage;
import com.example.crudmicroservice.chat.service.ChatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/v1/messages")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @Autowired
    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @PostMapping()
    public ResponseEntity<ChatMessage> createMessage(@RequestBody ChatMessageCreateDTO message) {
        log.info("Save new message: {}", message);
        try {
            ChatMessage chatMessage = chatMessageService.save(message);
            return ResponseEntity.ok(chatMessage);
        }
        catch (ChatMessageCreateException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ChatMessage>> getAllChatMessages() {
        log.info("Requesting all chat messages...");
        try {
            List<ChatMessage> chatMessages = chatMessageService.getAllChatMessage();
            return ResponseEntity.ok(chatMessages);
        }
        catch (ChatMessageNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<ChatMessage> getChatMessageByMessageId(@PathVariable String messageId) {
        log.info("Requesting message with ID: {}", messageId);
        try {
            ChatMessage chatMessage = chatMessageService.getChatMessageByMessageId(messageId);
            return ResponseEntity.ok(chatMessage);
        }
        catch (ChatMessageNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }




}
