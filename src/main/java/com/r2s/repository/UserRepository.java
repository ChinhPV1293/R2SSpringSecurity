package com.r2s.repository;

import com.r2s.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserRepository {
    public User findByUserName(String username) {
        User user = new User("chinhpv1293@gmail.com",
                "$2a$10$eF52Wh2Y8VYa3yoQn3lTwebWXsfD/tHIVcULmWuy44qXytyvBnprO",
                Arrays.asList(new SimpleGrantedAuthority("USER")));
        return user;
    }
}
