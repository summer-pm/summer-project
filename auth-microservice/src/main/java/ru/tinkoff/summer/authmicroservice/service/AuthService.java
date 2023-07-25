package ru.tinkoff.summer.authmicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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

    @LoadBalanced
    private final RestTemplate restTemplate;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, JwtService jwtService, RestTemplate restTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.restTemplate = restTemplate;
    }

    @Value("${link.database.save}")
    private String DATABASE_SAVE_URL;

    public void saveUser(UserDTO newUser) throws UserAlreadyExistsException {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        HttpEntity<UserDTO> request = new HttpEntity<>(newUser);
        ResponseEntity<String> responseEntity
                = restTemplate.exchange(DATABASE_SAVE_URL, HttpMethod.POST, request, String.class);

        log.info("database url: {}", DATABASE_SAVE_URL);
        if (responseEntity.getStatusCode() != HttpStatus.OK)
            throw new UserAlreadyExistsException("Пользователь с такой почтой уже существует");

    }

    public String generateToken(String email) {
        return jwtService.generateToken(email);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
