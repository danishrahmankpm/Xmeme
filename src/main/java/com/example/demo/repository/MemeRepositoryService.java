package com.example.demo.repository;

import java.util.List;
import com.example.demo.Model.MemeModel;
import com.example.demo.data.MemeEntity;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

@Service
public class MemeRepositoryService {

    @Autowired
    private MemeRepository memeRepository;
  
    @Autowired
    private ModelMapper modelMapper;

    public List<MemeModel> getMemefeed() {
        Pageable pageable = PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "_id"));
        Page<MemeModel> memesPage = memeRepository.findAll(pageable);
        return memesPage.getContent();
    }
    

    public MemeEntity postMeme(MemeModel memeModel) {
        MemeModel post=memeRepository.save(memeModel);
        return modelMapper.map(post,MemeEntity.class);
    }

    public Optional getMemeById(String id){
        return memeRepository.findById(id);
    }

    public void deleteAll() {
        memeRepository.deleteAll();
    }

    public Optional<MemeModel> findByNameAndCaptionAndUrl(String name, String caption,
            String url) {
        Optional<MemeModel> memeModel=memeRepository.findByNameAndCaptionAndUrl(name, caption, url);
        return memeModel;
    } 
    
}


    





