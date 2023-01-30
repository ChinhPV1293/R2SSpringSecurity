package com.r2s.service.impl;

import com.r2s.config.JwtTokenUtil;
import com.r2s.entity.User;
import com.r2s.model.LoginRequest;
import com.r2s.model.RegisterRequest;
import com.r2s.repository.UserRepository;
import com.r2s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequest authenticationRequest) {
        User user = userRepository.findByUserName(authenticationRequest.getUsername());

        if(ObjectUtils.isEmpty(user)){
            throw new UsernameNotFoundException("User Not Found Exception");
        } else {
            if(!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())){
                throw new AuthenticationServiceException("Wrong password");
            }
        }
        return jwtTokenUtil.generateToken(user);
    }

    @Override
    public User register(RegisterRequest registerRequest) {
        String password = registerRequest.getPassword();
        String cypherText = passwordEncoder.encode(password);
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(cypherText);
        user.setRoles(Arrays.asList(new SimpleGrantedAuthority("USER")));
        return user;
    }
}
