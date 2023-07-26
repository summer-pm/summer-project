package com.example.crudmicroservice.chat.controller;


import com.example.crudmicroservice.chat.dto.ChatRoomCreateDTO;
import com.example.crudmicroservice.chat.dto.ChatRoomTransferDTO;
import com.example.crudmicroservice.chat.exception.ChatRoomCreateException;
import com.example.crudmicroservice.chat.exception.ChatRoomNotFoundException;
import com.example.crudmicroservice.chat.model.ChatRoom;
import com.example.crudmicroservice.chat.model.ChatUser;
import com.example.crudmicroservice.chat.repository.ChatUserRepository;
import com.example.crudmicroservice.chat.repository.mongotemplate.ChatUserDALImpl;
import com.example.crudmicroservice.chat.service.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/rooms")
public class ChatRoomController {

    @Autowired
    private ChatUserDALImpl chatUserDALImpl;

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping()
    public ResponseEntity<ChatRoom> createNewRoom(@RequestBody ChatRoomCreateDTO chatRoomCreateDTO) {
        try {
            ChatRoom chatRoom = chatRoomService.save(chatRoomCreateDTO);
            log.info("Created new room: {}", chatRoom.toString());
            return ResponseEntity.ok(chatRoom);
        }
        catch (ChatRoomCreateException e) {
            log.error("Error saving chat room: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        catch (Exception ex) {
            log.error("Error saving chat room: {}", ex.getMessage());
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

    @GetMapping("/list")
    public ResponseEntity<List<ChatRoomTransferDTO>> getChatRoomsByUserId(@RequestParam("userId") String userId) {
        log.info("Requesting list of chats by userID: {}", userId);
        try {
            List<ChatRoomTransferDTO> chatRooms = chatRoomService.getChatRoomsByUserId(userId);
            return ResponseEntity.ok(chatRooms);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }


    @DeleteMapping
    public ResponseEntity<Void> deleteChatRoom(@RequestParam("roomId") String roomId) {
        try {
            chatRoomService.deleteRoom(roomId);
            log.info("Room with roomId: {}, deleted.", roomId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }














}
