package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exchange.UserDto.CreateUserDto;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService; 

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CreateUserDto createUserDto) {
        if (createUserDto == null || createUserDto.getName() == null || createUserDto.getEmail() == null) {
            return ResponseEntity.badRequest().body("Invalid user data");
        }
        String userId = userService.registerUser(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered with ID: " + userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(String userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    

    
}
