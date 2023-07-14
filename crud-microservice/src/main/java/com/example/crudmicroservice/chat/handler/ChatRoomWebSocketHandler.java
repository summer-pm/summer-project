package com.example.crudmicroservice.chat.handler;

import com.example.crudmicroservice.chat.service.ChatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
public class ChatRoomWebSocketHandler extends TextWebSocketHandler {
    private Map<String, Set<WebSocketSession>> roomSessions = new ConcurrentHashMap<>();
    @Autowired
    private ChatMessageService messageService; // Сервис для работы с сообщениями

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection established");
        // Получение идентификатора комнаты из пути URL
        String roomId = extractRoomId(session.getUri().getPath());

        String sessionId = UUID.randomUUID().toString();
        session.getAttributes().put("sessionId", sessionId);

        // Получение существующего набора сессий для комнаты или создание нового
        Set<WebSocketSession> sessions = roomSessions
                .computeIfAbsent(roomId, key -> new CopyOnWriteArraySet<>());

        // Добавление текущей сессии в набор сессий комнаты
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Received text message: {}. From {}", message.getPayload(), session.getId());
        // Получение идентификатора комнаты из пути URL
        String roomId = extractRoomId(session.getUri().getPath());



        // Получение существующего набора сессий для комнаты
        Set<WebSocketSession> sessions = roomSessions.get(roomId);
        log.info("Sessions: " + sessions.stream().toList());
        // Отправка сообщения всем сессиям в комнате
        for (WebSocketSession roomSession : sessions) {
            roomSession.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Connection closed");
        // Получение идентификатора комнаты из пути URL
        String roomId = extractRoomId(session.getUri().getPath());

        // Получение существующего набора сессий для комнаты
        Set<WebSocketSession> sessions = roomSessions.get(roomId);
        log.info("Sessions: " + sessions.stream().toList());
        // Удаление текущей сессии из набора сессий комнаты
        sessions.remove(session);

        // Если все сессии в комнате закрыты, удалить комнату из map
        if (sessions.isEmpty()) {
            roomSessions.remove(roomId);
        }
    }
    private String extractRoomId(String path) {
        // Извлечение идентификатора комнаты из пути URL
        return path.substring(path.lastIndexOf('/') + 1);
    }
}
