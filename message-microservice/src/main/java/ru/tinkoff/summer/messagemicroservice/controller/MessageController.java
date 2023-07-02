package ru.tinkoff.summer.messagemicroservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    @GetMapping("/view")
    public String view() {
        return "Response from controller in Message Microservice_v2";
    }

}
