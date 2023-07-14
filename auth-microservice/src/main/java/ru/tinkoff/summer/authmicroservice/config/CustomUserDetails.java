package ru.tinkoff.summer.authmicroservice.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.tinkoff.summer.authmicroservice.entity.UserCredential;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String email;
    private String password;

    public CustomUserDetails(UserCredential userCredential) {
        this.email = userCredential.getEmail();
        this.password = userCredential.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
