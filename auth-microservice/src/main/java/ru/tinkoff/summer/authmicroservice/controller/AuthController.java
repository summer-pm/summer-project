package ru.tinkoff.summer.authmicroservice.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ru.tinkoff.summer.authmicroservice.dto.AuthRequest;
import ru.tinkoff.summer.authmicroservice.dto.Token;
import ru.tinkoff.summer.authmicroservice.dto.UserDTO;
import ru.tinkoff.summer.authmicroservice.exception.UserAlreadyExistsException;
import ru.tinkoff.summer.authmicroservice.service.AuthService;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody UserDTO newUser) {
        try {
            authService.saveUser(newUser);
            return ResponseEntity.created(URI.create("")).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Token> getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate
                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        log.info("Authenticate: {}", authenticate.toString());
        if (authenticate.isAuthenticated()) {
            return ResponseEntity.ok(new Token(authService.generateToken(authRequest.getEmail())));
        } else {
            log.info("Inside else of login endpoint");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
