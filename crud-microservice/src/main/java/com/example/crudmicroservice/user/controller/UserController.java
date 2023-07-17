package com.example.crudmicroservice.user.controller;

import com.example.crudmicroservice.user.dto.RegisterRequest;
import com.example.crudmicroservice.user.dto.UserCredentialsInfo;
import com.example.crudmicroservice.user.dto.UserEmail;
import com.example.crudmicroservice.user.exception.UserNotFoundException;
import com.example.crudmicroservice.user.model.User;
import com.example.crudmicroservice.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody RegisterRequest newUser) {
        try {
            String message = userService.saveUser(newUser);
            return ResponseEntity.ok(message);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Пользователь с такой почтой уже существует");
        }
    }

    @PostMapping("/user")
    public UserCredentialsInfo getUserCredentials(@RequestBody UserEmail userEmail) {
        try {
            return userService.getUserCredentials(userEmail.getEmail());
        } catch (UserNotFoundException exception) {
            log.info("Got into controller catch block. Exception: {}", exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", exception);
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
