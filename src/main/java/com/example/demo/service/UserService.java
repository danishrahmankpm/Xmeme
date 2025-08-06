package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.demo.Model.MemeModel;
import com.example.demo.Model.UserModel;
import com.example.demo.exchange.UserDto.CreateUserDto;
import com.example.demo.exchange.UserDto.UserDto;
import com.example.demo.repository.MemeRepository;
import com.example.demo.repository.UserRepository;

public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MemeRepository memeRepository;
    
    public List<MemeModel> getMemesByUser(String username) throws NotFoundException {
        UserModel user = userRepository.findByUserName(username)
            .orElseThrow(() -> new NotFoundException());
        List<String> memeIds = user.getMemes();
        List<MemeModel> memes = memeRepository.findAllById(memeIds);
        return memes;
    }
    
    public void deleteUser(String userId) throws NotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException();
        }
        userRepository.deleteById(userId);
    }

    public UserDto getUserByUsername(String username) throws NotFoundException {
        UserModel userModel = userRepository.findByUserName(username)
            .orElseThrow(() -> new NotFoundException());
        UserDto userDto = new UserDto();
        userDto.setUsername(userModel.getUsername());
        userDto.setName(userModel.getName());
        userDto.setEmail(userModel.getEmail());
       
        return userDto;
    }
    
}
