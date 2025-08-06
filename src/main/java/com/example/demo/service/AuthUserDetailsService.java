package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import com.example.demo.Model.UserModel;
import com.example.demo.repository.UserRepository;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isEmpty()) {
            throw new UsernameNotFoundException("Username cannot be null or empty");
        }
        Optional<UserModel> user = userRepository.findByUserName(username);

        if (user.isPresent()) {
            return new User(
                user.get().getUsername(),
                user.get().getPassword(),
                List.of() 
            );
        } else {
            throw new UsernameNotFoundException("User not found with id: " + username);
        }
    }

    
}
