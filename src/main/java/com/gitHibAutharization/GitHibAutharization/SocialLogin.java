package com.gitHibAutharization.GitHibAutharization;


import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SocialLogin extends WebSecurityConfigurerAdapter {

	@GetMapping("/user")
	public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
		OAuthUser user = new OAuthUser(principal);
		Map<String, Object> dataUser = new HashMap<>();
//
		dataUser.put("name", user.getName());
		dataUser.put("email", user.getEmail());
		dataUser.put("login", user.getLodin());
		dataUser.put("id", user.getId());

		System.out.println(dataUser);
		return dataUser;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
				.authorizeRequests(a -> a
						.antMatchers("/", "/error", "/webjars/**").permitAll()  // делает видимой страницу для всех пользователей зарегестрированныых и нет
						.anyRequest().authenticated()
				)
				.exceptionHandling(e -> e
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) // генерация ошибки 401
				)
				.csrf(c -> c
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				)
				.logout(l -> l
						.logoutSuccessUrl("/").permitAll()
				)
				.oauth2Login();
		// @formatter:on
	}

	public static void main(String[] args) {
		SpringApplication.run(SocialLogin.class, args);
	}

}
