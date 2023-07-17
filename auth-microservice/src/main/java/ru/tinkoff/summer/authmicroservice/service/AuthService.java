package ru.tinkoff.summer.authmicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tinkoff.summer.authmicroservice.dto.UserDTO;

@Service
@Slf4j
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final RestTemplate restTemplate;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, JwtService jwtService, RestTemplate restTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> saveUser(UserDTO newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        HttpEntity<UserDTO> request = new HttpEntity<>(newUser);

        String DATABASE_SAVE_URL = "http://localhost:8086/users";
        ResponseEntity<String> responseEntity
                = ResponseEntity.badRequest().body("Пользователь с такой почтой уже существует");
        try {
            responseEntity = restTemplate.exchange(DATABASE_SAVE_URL, HttpMethod.POST, request, String.class);
            return responseEntity;
        } catch (Exception ex) {
            return responseEntity;
        }
    }

    public String generateToken(String email) {
        return jwtService.generateToken(email);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
