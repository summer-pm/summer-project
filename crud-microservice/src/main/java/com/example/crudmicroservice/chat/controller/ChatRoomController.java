package com.example.crudmicroservice.chat.controller;


import com.example.crudmicroservice.chat.dto.ChatRoomCreateDTO;
import com.example.crudmicroservice.chat.exception.ChatRoomCreateException;
import com.example.crudmicroservice.chat.exception.ChatRoomNotFoundException;
import com.example.crudmicroservice.chat.model.ChatRoom;
import com.example.crudmicroservice.chat.service.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/rooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping()
    public ResponseEntity<ChatRoom> createNewRoom(@RequestBody ChatRoomCreateDTO chatRoomCreateDTO) {
        log.info("Creating new Chat Room...");
        try {
            ChatRoom chatRoom = chatRoomService.save(chatRoomCreateDTO);
            return ResponseEntity.ok(chatRoom);
        }
        catch (ChatRoomCreateException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ChatRoom> getChatRoomById(@PathVariable String roomId) {
        log.info("Requesting Chat Room with ID: {}", roomId);
        try {
            ChatRoom chatRoom = chatRoomService.getChatRoomByRoomId(roomId);
            return ResponseEntity.ok(chatRoom);
        }
        catch (ChatRoomNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<ChatRoom>> getAllChatRoom() {
        log.info("Requesting all Chat Rooms");
        try {
            List<ChatRoom> chatRooms = chatRoomService.getAllChatRoom();
            return ResponseEntity.ok(chatRooms);
        }
        catch (ChatRoomNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }



















    // TODO метод отправки сообщения в топик (версия с минимальной веб мордой). Пока не удалять - удалю сам.
//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;
//
//    @MessageMapping("/chat/{to}")
//    public void sendMessage(@DestinationVariable String  to, ChatMessage message) {
//        log.info("Handling send message: " + message + "to: " + to);
//        boolean isExist = UserStorage.getInstance().getUsers().contains(to);
//
//        if (isExist) {
//            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
//        }
//    }
}
