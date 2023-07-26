package com.example.crudmicroservice.chat.controller;


import com.example.crudmicroservice.chat.dto.ChatUserCreateDTO;
import com.example.crudmicroservice.chat.exception.ChatUserCreateException;
import com.example.crudmicroservice.chat.exception.ChatUserNotFoundException;
import com.example.crudmicroservice.chat.model.ChatUser;
import com.example.crudmicroservice.chat.service.ChatUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("api/v1/chat/users")
public class ChatUserController {

    private final ChatUserService chatUserService;
    @Autowired
    public ChatUserController(ChatUserService chatUserService) {
        this.chatUserService = chatUserService;
    }

    @GetMapping("")
    public ResponseEntity<List<ChatUser>> getAllUser() {
        log.info("Requesting all users...");
        try {
            List<ChatUser> chatUsers = chatUserService.getAllChatUser();
            return ResponseEntity.ok(chatUsers);
        }
        catch (ChatUserCreateException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ChatUser> saveUser(@RequestBody ChatUserCreateDTO chatUserCreateDTO) {
        log.info("Creating new user...");
        try {
            ChatUser chatUser = chatUserService.saveChatUser(chatUserCreateDTO);
            return ResponseEntity.ok(chatUser);
        }
        catch (ChatUserCreateException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{userPgId}")
    public ResponseEntity<ChatUser> getUserByPgId(@PathVariable String userPgId) {
        log.info("Requesting user with ID: {}", userPgId);
        try {
            ChatUser chatUser = chatUserService.getChatUserByPgId(userPgId);
            return ResponseEntity.ok(chatUser);
        }
        catch (ChatUserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChatUser> deleteChatUser(@PathVariable String id) {
        log.info("Delete ChatUser with id {}", id);
        try {
            ChatUser chatUser = chatUserService.deleteChatUser(id);
            return ResponseEntity.ok(chatUser);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
