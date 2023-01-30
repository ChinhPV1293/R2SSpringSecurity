package com.r2s.service;

import com.r2s.entity.User;
import com.r2s.model.LoginRequest;
import com.r2s.model.RegisterRequest;

public interface UserService {
    String login(LoginRequest authenticationRequest);

    User register(RegisterRequest registerRequest);
}
