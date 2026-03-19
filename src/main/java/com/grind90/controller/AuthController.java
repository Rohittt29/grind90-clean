package com.grind90.controller;

import com.grind90.dto.LoginRequest;
import com.grind90.dto.LoginResponse;
import com.grind90.entity.User;
import com.grind90.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    // SIGNUP
    @PostMapping("/signup")
    public User signup(@RequestBody User user){
        return authService.registerUser(user);
    }

    // LOGIN
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){

        String token = authService.login(
                request.getEmail(),
                request.getPassword()
        );

        return new LoginResponse(token);
    }
}