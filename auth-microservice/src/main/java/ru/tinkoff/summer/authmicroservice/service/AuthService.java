package ru.tinkoff.summer.authmicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tinkoff.summer.authmicroservice.dto.UserDTO;
import ru.tinkoff.summer.authmicroservice.exception.UserAlreadyExistsException;

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

    public String saveUser(UserDTO newUser) throws UserAlreadyExistsException {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        HttpEntity<UserDTO> request = new HttpEntity<>(newUser);

        String DATABASE_SAVE_URL = "http://localhost:8086/users";
        ResponseEntity<String> responseEntity
                = restTemplate.exchange(DATABASE_SAVE_URL, HttpMethod.POST, request, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            throw new UserAlreadyExistsException("Пользователь с такой почтой уже существует");
        }
    }

    public String generateToken(String email) {
        return jwtService.generateToken(email);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
