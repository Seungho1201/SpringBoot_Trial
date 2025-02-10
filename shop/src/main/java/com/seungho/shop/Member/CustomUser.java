package com.seungho.shop.Member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

// User 클래스를 커스터마이징 해보자
public class CustomUser extends User {

    public String displayName;

    public CustomUser(String username,
                      String password,
                      Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
    }
}