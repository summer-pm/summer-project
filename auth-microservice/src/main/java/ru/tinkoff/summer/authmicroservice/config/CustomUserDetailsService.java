package ru.tinkoff.summer.authmicroservice.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ru.tinkoff.summer.authmicroservice.dto.UserCredentialsInfo;
import ru.tinkoff.summer.authmicroservice.dto.UserEmail;
import ru.tinkoff.summer.authmicroservice.handler.RestTemplateErrorHandler;

import java.util.Objects;


@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final RestTemplate restTemplate;

    @Autowired
    public CustomUserDetailsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String DATABASE_GET_USER_URL = "http://localhost:8086/users/user";
        UserEmail userEmail = new UserEmail(email);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserEmail> request = new HttpEntity<>(userEmail, headers);
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());


        ResponseEntity<UserCredentialsInfo> response
                = restTemplate.exchange(DATABASE_GET_USER_URL, HttpMethod.POST, request, UserCredentialsInfo.class);
        log.info("Response after external API request: {}", response);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Response after external API request succeeded: {}", response);
            UserCredentialsInfo userCredentialsInfo = response.getBody();

            CustomUserDetails customUserDetails = null;
            if (userCredentialsInfo != null) {
                customUserDetails = new CustomUserDetails(userCredentialsInfo);
            }
            return customUserDetails;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}


//        ResponseEntity<UserCredentialsInfo> responseEntity;
//
//        try {
//            responseEntity
//                    = restTemplate.exchange(DATABASE_GET_USER_URL, HttpMethod.POST, request, UserCredentialsInfo.class);
//            log.info("Successful exchange: {}", responseEntity);
//        } catch (UsernameNotFoundException ex) {
//            responseEntity = ResponseEntity.badRequest().build();
//            log.info("Unsuccessful exchange: {}", responseEntity);
//            return new CustomUserDetails(Objects.requireNonNull(responseEntity.getBody()));
////            throw new UsernameNotFoundException("Пользователь с такой почтой не найден");
//        }

//        UserCredentialsInfo userCredentialsInfo = responseEntity.getBody();
//
//        if (userCredentialsInfo != null) {
//            return new CustomUserDetails(userCredentialsInfo);
//        } else {
//            throw new UsernameNotFoundException("Пользователь с такой почтой не найден");
//        }