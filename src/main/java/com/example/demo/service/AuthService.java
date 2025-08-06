package com.example.demo.service;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.Model.UserModel;
import com.example.demo.Util.JwtUtil;
import com.example.demo.exchange.AuthDto.AuthResponse;
import com.example.demo.exchange.UserDto.CreateUserDto;
import com.example.demo.repository.UserRepository;

public class AuthService {
    @Autowired
    private JwtUtil jwtUtil;  
    @Autowired 
    private AuthenticationManager authManager; 
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository; 
    public AuthResponse login (String username, String password) throws AuthenticationException {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        String token= jwtUtil.generateToken(username);
        AuthResponse authResponse = new AuthResponse(username, token);
        return authResponse; 
    }
    public AuthResponse register(CreateUserDto createUserDto) {
        UserModel user = new UserModel();
        user.setUsername(createUserDto.getUsername());
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        
        userRepository.save(user);
        
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(user.getUsername(), token);
    }
}
