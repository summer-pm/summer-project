package ru.tinkoff.summer.authmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.summer.authmicroservice.entity.UserCredential;
import ru.tinkoff.summer.authmicroservice.exception.UserAlreadyExistsException;
import ru.tinkoff.summer.authmicroservice.repository.UserCredentialRepository;

import java.util.Optional;

@Service
public class AuthService {

    private final UserCredentialRepository userCredentialRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Autowired
    public AuthService(UserCredentialRepository userCredentialRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserCredential saveUser(UserCredential userCredential) throws UserAlreadyExistsException {
        Optional<UserCredential> userDB = userCredentialRepository.findByEmail(userCredential.getEmail());
        if (userDB.isEmpty()) {
            userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
            return userCredentialRepository.save(userCredential);
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
