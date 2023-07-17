package com.example.crudmicroservice.user.controller;

import com.example.crudmicroservice.user.model.UserPosts;
import com.example.crudmicroservice.user.service.UserPostsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-posts")
public class UserPostsController {

    private final UserPostsService userPostsService;

    public UserPostsController(UserPostsService userPostsService) {
        this.userPostsService = userPostsService;
    }

    @PostMapping
    public ResponseEntity<UserPosts> createUserPosts(@RequestBody UserPosts userPosts) {
        UserPosts createdUserPosts = userPostsService.saveUserPosts(userPosts);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserPosts);
    }

    @PatchMapping("/{userPostsId}")
    public ResponseEntity<UserPosts> updateUserPosts(@PathVariable Long userPostsId, @RequestBody UserPosts userPosts) {
        UserPosts updatedUserPosts = userPostsService.updateUserPost(userPostsId, userPosts);
        if (updatedUserPosts != null) {
            return ResponseEntity.ok(updatedUserPosts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userPostsId}")
    public ResponseEntity<UserPosts> getUserPostsById(@PathVariable Long userPostsId) {
        UserPosts userPosts = userPostsService.getUserPostsById(userPostsId);
        if (userPosts != null) {
            return ResponseEntity.ok(userPosts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userPostsId}")
    public ResponseEntity<Void> deleteUserPosts(@PathVariable Long userPostsId) {
        userPostsService.deleteUserPosts(userPostsId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserPosts>> getAllUserPosts() {
        List<UserPosts> userPostsList = userPostsService.getAllUserPosts();
        return ResponseEntity.ok(userPostsList);
    }
}
