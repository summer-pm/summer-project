package com.example.crudmicroservice.service;

import com.example.crudmicroservice.model.SolutionsAttempts;
import com.example.crudmicroservice.model.User;
import com.example.crudmicroservice.model.UserPosts;
import com.example.crudmicroservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserPostsService userPostsService;

    private final SolutionsAttemptsService solutionsAttemptsService;

    public UserService(UserRepository userRepository, UserPostsService userPostsService, SolutionsAttemptsService solutionsAttemptsService) {
        this.userRepository = userRepository;
        this.userPostsService = userPostsService;
        this.solutionsAttemptsService = solutionsAttemptsService;
    }

    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        if (user.getUserPostsList() != null) {
            for (UserPosts userPosts : user.getUserPostsList()) {
                userPosts.setUser(savedUser);
                userPostsService.saveUserPosts(userPosts);
            }
        }
        if (user.getSolutionsAttemptsList() != null) {
            for (SolutionsAttempts solutionsAttempts : user.getSolutionsAttemptsList()) {
                solutionsAttempts.setUser(savedUser);
                solutionsAttemptsService.saveSolutionsAttempts(solutionsAttempts);
            }
        }
        return savedUser;
    }

    public User updateUser(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        existingUser.setUserId(userId);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setUserImage(updatedUser.getUserImage());
        existingUser.setDateOfBirth(updatedUser.getDateOfBirth());
        existingUser.setDateOfRegistration(updatedUser.getDateOfRegistration());
        existingUser.setDateOfEdit(updatedUser.getDateOfEdit());
        existingUser.setDateOfLastActivity(updatedUser.getDateOfLastActivity());
        existingUser.setUserPostsList(updatedUser.getUserPostsList());
        existingUser.setSolutionsAttemptsList(updatedUser.getSolutionsAttemptsList());

        return userRepository.save(existingUser);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}