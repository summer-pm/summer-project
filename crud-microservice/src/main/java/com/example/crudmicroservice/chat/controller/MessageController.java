package com.example.crudmicroservice.chat.controller;


import com.example.crudmicroservice.chat.dto.ChatMessageCreateDTO;
import com.example.crudmicroservice.chat.dto.MessageDTO;
import com.example.crudmicroservice.chat.model.ChatMessage;
import com.example.crudmicroservice.chat.model.ChatRoom;
import com.example.crudmicroservice.chat.model.ChatUser;
import com.example.crudmicroservice.chat.model.Status;
import com.example.crudmicroservice.chat.service.ChatMessageService;
import com.example.crudmicroservice.chat.service.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/chat")
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/sendMessage/{roomId}")
    public void sendMessage(@DestinationVariable String roomId, MessageDTO message) {
        // Обработка входящего сообщения и рассылка его всем подключенным клиентам в комнате

        // Определяем топик для рассылки сообщения в комнате
        String topic = "/topic/" + roomId;
        log.info("Received message: {}", message.toString());
        ChatMessageCreateDTO create = new ChatMessageCreateDTO();
        create.setContent(message.getContent());
        create.setChatRoomId(roomId);
        create.setSenderId("SenderId from client");

        ChatMessage chatMessage = chatMessageService.save(create);
        // Отправляем сообщение всем подключенным клиентам в указанной комнате
        messagingTemplate.convertAndSend(topic, chatMessage);
    }


    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        // Обработчик события подключения WebSocket
        log.info("New WebSocket connection: {}", event.toString());

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        // Извлекаем ID комнаты из заголовка запроса
        String roomId = accessor.getFirstNativeHeader("roomId");
        log.info("Session: {}", accessor.getSessionId());
        if (roomId != null) {
            // Связываем WebSocket-соединение с указанной комнатой
//            chatRoomService.addUserToRoom(roomId, accessor.getSessionId());
        }
    }
}
