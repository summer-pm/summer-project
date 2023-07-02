package ru.tinkoff.summer.leetcodemicroservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/leetcode")
public class LeetcodeController {

    @GetMapping("/view")
    public String getSomeCode() {
        return "System.out.println('Something code from LeetcodeController')";
    }
}
