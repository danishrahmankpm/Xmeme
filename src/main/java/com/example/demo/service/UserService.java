package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import com.example.demo.Model.UserModel;
import com.example.demo.exchange.UserDto.CreateUserDto;
import com.example.demo.repository.UserRepository;

public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public String registerUser(CreateUserDto createUserDto) {
        UserModel userModel = new UserModel();
        userModel.setName(createUserDto.getName()); 
        userModel.setEmail(createUserDto.getEmail());

        userRepository.save(userModel);
        return userModel.getId(); 
        
    }
    public void deleteUser(String userId) throws NotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException();
        }
        userRepository.deleteById(userId);
    }
    
}
