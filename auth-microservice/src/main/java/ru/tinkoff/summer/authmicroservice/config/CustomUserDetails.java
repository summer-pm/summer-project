package ru.tinkoff.summer.authmicroservice.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.tinkoff.summer.authmicroservice.dto.UserCredentialsInfo;
import ru.tinkoff.summer.authmicroservice.dto.UserDTO;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String email;
    private String password;

    public CustomUserDetails(UserCredentialsInfo userCredentialsInfo) {
        this.email = userCredentialsInfo.getEmail();
        this.password = userCredentialsInfo.getPassword();
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
