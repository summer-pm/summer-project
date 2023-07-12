package com.example.crudmicroservice.controller;

import com.example.crudmicroservice.model.SolutionsAttempts;
import com.example.crudmicroservice.service.SolutionsAttemptsService;
import com.example.crudmicroservice.service.TaskService;
import com.example.crudmicroservice.service.UserPostsService;
import com.example.crudmicroservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainController {

    private final UserService userService;
    private final UserPostsService userPostsService;
    private final TaskService taskService;

    private final SolutionsAttemptsService attemptsService;

    public mainController(TaskService taskService, UserService userService, UserPostsService userPostsService, SolutionsAttemptsService attemptsService) {
        this.taskService = taskService;
        this.userService = userService;
        this.userPostsService = userPostsService;
        this.attemptsService = attemptsService;
    }

    @GetMapping("/hello")
    public String hello() {
//        User user = new User();
//        user.setEmail("EmailUser1");
//        user.setPhoneNumber("phoneNumber1");
//        user.setUsername("updateUserName!1");
//        user.setPassword("qwerty");
//        user.setUserImage("link");
//        user.setDateOfBirth(LocalDateTime.of(2000, 1, 1, 0, 0, 1));
//        user.setDateOfRegistration(LocalDateTime.now());
//        user.setDateOfEdit(LocalDateTime.now());
//        user.setDateOfLastActivity(LocalDateTime.now());
//
//        UserPosts post1 = new UserPosts();
//        post1.setCommentary("sfasdfasf");
//
//        UserPosts post2 = new UserPosts();
//        post2.setUser(user);
//        post2.setCommentary("asdfasfwe23");
//
//        UserPosts post3 = new UserPosts();
//        post3.setUser(user);
//        post3.setCommentary("last");
//
//        List<UserPosts> postsList = new ArrayList<>();
//        postsList.add(post1);
//        postsList.add(post2);
//        postsList.add(post3);
//
//        user.setUserPostsList(postsList);
//        userService.saveUser(user);
//
//        System.out.println(attemptsService.getSolutionsAttemptsById(1L) + "    123");

        SolutionsAttempts attempts = new SolutionsAttempts();
        attempts.setCode("fasaf");
        attempts.setStatus("fsasfasf");
        attempts.setLanguage("sfasfafaf");
        attemptsService.saveSolutionsAttempts(attempts);


        return "HEllO WORLD!!!";
    }
}
