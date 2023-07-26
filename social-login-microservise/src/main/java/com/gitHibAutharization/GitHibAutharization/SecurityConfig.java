package com.gitHibAutharization.GitHibAutharization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/public/**").permitAll() // Разрешить доступ к публичным ресурсам
                .antMatchers("/admin/**").hasRole("ADMIN") // Только для пользователей с ролью ADMIN
                .anyRequest().authenticated() // Для всех остальных ресурсов требуется аутентификация
                .and()
                .formLogin(); // Включить форму логина
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // Настройка пользователей в памяти (для простоты, не используйте в продакшене)
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}adminpassword").roles("ADMIN");
    }
}

