package com.example.crudmicroservice.user.service;

import com.example.crudmicroservice.chat.dto.ChatUserCreateDTO;
import com.example.crudmicroservice.chat.model.ChatUser;
import com.example.crudmicroservice.chat.repository.ChatUserRepository;
import com.example.crudmicroservice.chat.service.ChatUserService;
import com.example.crudmicroservice.task.model.SolutionsAttempts;
import com.example.crudmicroservice.task.service.SolutionsAttemptsService;
import com.example.crudmicroservice.user.dto.RegisterRequest;
import com.example.crudmicroservice.user.dto.UserCredentialsInfo;
import com.example.crudmicroservice.user.exception.UserAlreadyExistsException;
import com.example.crudmicroservice.user.exception.UserNotFoundException;
import com.example.crudmicroservice.user.repository.UserRepository;
import com.example.crudmicroservice.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserPostsService userPostsService;
    private final ChatUserRepository chatUserRepository;

    private final SolutionsAttemptsService solutionsAttemptsService;

    public Optional<User> getUserByEmail(String email) throws UserNotFoundException  {
        return userRepository.findByEmail(email);

    }

    public UserService(UserRepository userRepository, UserPostsService userPostsService, ChatUserRepository chatUserRepository, SolutionsAttemptsService solutionsAttemptsService) {
        this.userRepository = userRepository;
        this.userPostsService = userPostsService;
        this.chatUserRepository = chatUserRepository;
        this.solutionsAttemptsService = solutionsAttemptsService;
    }

    public String saveUser(RegisterRequest newUser) {
        Optional<User> existUser = userRepository.findByEmail(newUser.getEmail());
        if (existUser.isEmpty()) {
            User user = setNewUser(newUser);
            userRepository.save(user);
            return "Пользователь успешно зарегестрирован!";
        } else {
            throw new UserAlreadyExistsException("Пользователь с такой почтой уже существует");
        }
    }

    public UserCredentialsInfo getUserCredentials(String email) throws UserNotFoundException {
        Optional<User> userDB = userRepository.findByEmail(email);
        if (userDB.isPresent()) {
            return setUserCred(userDB.get());
        } else {
            throw new UserNotFoundException("Пользователь с такой почтой не найден");
        }
    }

    private UserCredentialsInfo setUserCred(User userDB) {
        UserCredentialsInfo userCredentialsInfo = new UserCredentialsInfo();
        userCredentialsInfo.setEmail(userDB.getEmail());
        userCredentialsInfo.setPassword(userDB.getPassword());

        return userCredentialsInfo;
    }

    private User setNewUser(RegisterRequest newUser) {
        User user = new User();
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setUsername(newUser.getUsername());

        return user;
    }

    public User updateUser(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        for (SolutionsAttempts attempts : existingUser.getSolutionsAttemptsList()) {
        }

        return userRepository.save(updatedUser);
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

    public ChatUser getChatUserByPgIdOrCreateNew(String userId) {
            ChatUser chatUser = chatUserRepository.findChatUserByUserPgId(userId);
            if (chatUser != null) {
                return chatUser;
            }
            else {
                ChatUserCreateDTO chatUserCreateDTO = new ChatUserCreateDTO();
                chatUserCreateDTO.setUserPgId(userId);
                ChatUser user = convertDTOToEntity(chatUserCreateDTO);
                return chatUserRepository.save(user);
            }
    }

    private ChatUser convertDTOToEntity(ChatUserCreateDTO chatUserCreateDTO) {
        ChatUser chatUser = new ChatUser();
        chatUser.setUserPgId(chatUserCreateDTO.getUserPgId());
        chatUser.setRooms(chatUserCreateDTO.getRooms());
        return chatUser;
    }
}