package com.example.demo.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Util.JwtUtil;
import com.example.demo.exchange.AuthDto.AuthRequest;
import com.example.demo.exchange.AuthDto.AuthResponse;
import com.example.demo.exchange.UserDto.CreateUserDto;
import com.example.demo.service.AuthService;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {
            System.out.println("Received login request for userId: " + authRequest.getUsername());
            AuthResponse authResponse = authService.login(authRequest.getUsername(), authRequest.getPassword());
            return ResponseEntity.ok(authResponse);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody CreateUserDto signupRequest) {
        try {
            
            authService.register(signupRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Password updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user");
        }
    }
    
}
