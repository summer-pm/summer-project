package com.example.crudmicroservice.service;

import com.example.crudmicroservice.model.UserPosts;
import com.example.crudmicroservice.repository.UserPostsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPostsService {
    private final UserPostsRepository userPostsRepository;

    public UserPostsService(UserPostsRepository userPostsRepository) {
        this.userPostsRepository = userPostsRepository;
    }

    public UserPosts saveUserPosts(UserPosts userPosts) {
        return userPostsRepository.save(userPosts);
    }

    public UserPosts updateUserPost(Long userPostId, UserPosts userPosts) {
        UserPosts existingUserPost = userPostsRepository.findById(userPostId)
                .orElseThrow(() -> new IllegalArgumentException("UserPosts not Found"));
        userPosts.setUserPostsId(userPostId);

        return userPostsRepository.save(userPosts);
    }

    public UserPosts getUserPostsById(Long userPostsId) {
        return userPostsRepository.findById(userPostsId).orElse(null);
    }

    public List<UserPosts> getAllUserPosts() {
        return userPostsRepository.findAll();
    }

    public void deleteUserPosts(Long userPostsId) {
        userPostsRepository.deleteById(userPostsId);
    }
}