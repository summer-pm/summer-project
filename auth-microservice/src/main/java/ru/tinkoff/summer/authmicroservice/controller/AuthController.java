package ru.tinkoff.summer.authmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.summer.authmicroservice.dto.AuthRequest;
import ru.tinkoff.summer.authmicroservice.entity.UserCredential;
import ru.tinkoff.summer.authmicroservice.exception.UserAlreadyExistsException;
import ru.tinkoff.summer.authmicroservice.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity addNewUser(@RequestBody UserCredential user) {
        try {
            authService.saveUser(user);
            return ResponseEntity.ok("Пользователь успешно создан");
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.badRequest().body("Пользователь с такой почтой уже существует");
        }
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate
                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return authService.generateToken(authRequest.getEmail());
        } else {
            //throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad Credentials");
            return "Bad Credentials";
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
