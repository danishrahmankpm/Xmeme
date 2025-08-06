package com.example.demo.repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Model.UserModel;


public interface UserRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findByUserName(String username);
}
