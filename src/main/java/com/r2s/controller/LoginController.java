package com.r2s.controller;

import com.r2s.entity.User;
import com.r2s.model.RegisterRequest;
import com.r2s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.r2s.config.JwtTokenUtil;
import com.r2s.model.LoginRequest;
import com.r2s.model.JwtResponse;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest)
			throws Exception {

		String token = userService.login(authenticationRequest);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
		User user = userService.register(registerRequest);
		return ResponseEntity.ok(user);
	}

}
