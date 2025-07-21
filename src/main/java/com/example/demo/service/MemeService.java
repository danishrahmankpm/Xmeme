package com.example.demo.service;

import com.example.demo.Model.MemeModel;
import com.example.demo.exchange.MemeDto.MemeEntityDto;
import com.example.demo.exchange.MemeDto.MemePostDto;
import com.example.demo.repository.MemeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemeService {

    @Autowired
    private MemeRepository memeRepository;

    @Autowired 
    private ModelMapper modelMapper;

    public Page<MemeEntityDto> getMemeFeed() {
        Pageable pageable = PageRequest.of(0, 10); // Example: first page with 10 items
        Page<MemeModel> memePage = memeRepository.findAll(pageable);
        List<MemeEntityDto> memeDtos = memePage.getContent().stream()
            .map(memeModel -> modelMapper.map(memeModel, MemeEntityDto.class))
            .collect(Collectors.toList());
        return new PageImpl<>(memeDtos, pageable, memePage.getTotalElements());
    }

    public String postMeme(MemePostDto memePostDto) {
        MemeModel memeModel = modelMapper.map(memePostDto, MemeModel.class);
        memeRepository.save(memeModel);
        return memeModel.getId().toString();
    }

    public MemeEntityDto getMemeById(String id) throws NotFoundException {
        Optional<MemeModel> memeModel = memeRepository.findById(id);
        if (memeModel.isEmpty()) {
            throw new NotFoundException();
        }
        return modelMapper.map(memeModel.get(), MemeEntityDto.class);
    }

    public void deleteAll() {
        memeRepository.deleteAll();
    }

    public void deleteMemeById(String id) throws NotFoundException {
        if (!memeRepository.existsById(id)) {
            throw new NotFoundException();
        }
        memeRepository.deleteById(id);
    }
}
