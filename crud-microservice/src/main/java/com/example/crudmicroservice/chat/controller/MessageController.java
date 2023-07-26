package com.example.crudmicroservice.chat.controller;


import com.example.crudmicroservice.chat.dto.ChatMessageCreateDTO;
import com.example.crudmicroservice.chat.dto.MessageDTO;
import com.example.crudmicroservice.chat.exception.ChatMessageCreateException;
import com.example.crudmicroservice.chat.model.ChatMessage;
import com.example.crudmicroservice.chat.model.ChatRoom;
import com.example.crudmicroservice.chat.model.ChatUser;
import com.example.crudmicroservice.chat.model.Status;
import com.example.crudmicroservice.chat.service.ChatMessageService;
import com.example.crudmicroservice.chat.service.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@CrossOrigin
@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/changeMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public ResponseEntity<ChatMessage> changeMessage(@DestinationVariable String roomId,  ChatMessageCreateDTO message) {
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
}
