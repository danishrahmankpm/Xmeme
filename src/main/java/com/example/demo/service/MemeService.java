package com.example.demo.service;

import com.example.demo.Model.MemeModel;
import com.example.demo.data.MemeEntity;
import com.example.demo.exchange.MemePostDto;
import com.example.demo.exchange.PostResponseDto;
import com.example.demo.exchange.ResponseDto;
import com.example.demo.repository.MemeRepository;
import com.example.demo.repository.MemeRepositoryService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service

public class MemeService {

  @Autowired
  private MemeRepositoryService memeRepositoryService;

  @Autowired 
  private ModelMapper modelMapper;

  public ResponseDto getMeme(Integer id) {
    return null;
  }

  public ResponseDto getMemeFeed() {
    List<MemeModel> feed=memeRepositoryService.getMemefeed();
    List<MemeEntity> entities = feed.stream()
    .map(model -> modelMapper.map(model, MemeEntity.class))
    .collect(Collectors.toList());
    
    ResponseDto responseDto=new ResponseDto(entities);
    return responseDto;
  }

  public PostResponseDto postMeme(MemePostDto MemePostDto) {
      Optional<MemeModel> existing = memeRepositoryService.findByNameAndCaptionAndUrl(
      MemePostDto.getName(),
      MemePostDto.getCaption(),
      MemePostDto.getUrl()
  );

  if (existing.isPresent()) {
      // Return 409 Conflict if duplicate
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate meme");
  }
    MemeEntity memeEntity = new MemeEntity();
    memeEntity.setName(MemePostDto.getName());
    memeEntity.setCaption(MemePostDto.getCaption());
    memeEntity.setUrl(MemePostDto.getUrl());
    MemeEntity post=memeRepositoryService.postMeme(modelMapper.map(memeEntity, MemeModel.class));
    PostResponseDto postresponseDto=new PostResponseDto(post.getId());
    return postresponseDto;
  }

  public ResponseDto getMemeById(String id) {
    Optional<MemeModel> memeModel = memeRepositoryService.getMemeById(id);
    if (memeModel.isPresent()) {
        MemeEntity entity = modelMapper.map(memeModel.get(), MemeEntity.class);
        return new ResponseDto(List.of(entity));
    } else {
        return new ResponseDto(List.of());
    }
  }

public ResponseDto deleteAll() {
    memeRepositoryService.deleteAll();
    return new ResponseDto() ;
}
}
