package ru.tinkoff.summer.usermicroservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("/view")
    public String user() {
        return "{name: Vasy; age: -1; description: Sample entity for test controller}";
    }
}
