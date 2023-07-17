package com.gitHibAutharization.GitHibAutharization;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static java.util.Collections.singletonMap;

public class OAuthUser implements OAuth2User {

    private OAuth2User oauth2User;

    public OAuthUser(OAuth2User oauth2User) {
        this.oauth2User = oauth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }
    public String getEmail() {
        return oauth2User.<String>getAttribute("email");
    }
    public int getId() {return (oauth2User.getAttribute("id"));}
    public String getLodin() {
        return oauth2User.<String>getAttribute("login");
    }
    public String getOauth2ClientName() {
        return this.getName();
    }
}