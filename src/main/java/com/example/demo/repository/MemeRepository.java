package com.example.demo.repository;

import java.util.Optional;
import com.example.demo.Model.MemeModel;
import com.example.demo.data.MemeEntity;
import com.example.demo.exchange.ResponseDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepository extends MongoRepository<MemeModel, String> {

    
    Optional<MemeModel> findByNameAndCaptionAndUrl(String name, String caption, String memeUrl);

    Optional<MemeModel> findById(String id);
        

}
