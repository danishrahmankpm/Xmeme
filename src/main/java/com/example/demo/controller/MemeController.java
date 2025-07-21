package com.example.demo.controller;

import jakarta.validation.Valid;
import com.example.demo.exchange.MemeDto.MemeEntityDto;
import com.example.demo.exchange.MemeDto.MemePostDto;
import com.example.demo.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/memes")
public class MemeController {

    @Autowired
    private MemeService memeService;

    @GetMapping("/feed")
    public ResponseEntity<Page<MemeEntityDto>> getMemes() {
        Page<MemeEntityDto> memes = memeService.getMemeFeed();
        if (memes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(memes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemeEntityDto> getMemeById(@PathVariable String id) {
        try {
            MemeEntityDto meme = memeService.getMemeById(id);
            return ResponseEntity.ok(meme);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity<String> postMeme(@Valid @RequestBody MemePostDto memePostDto) {
        if (memePostDto == null || memePostDto.getName() == null || memePostDto.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(memeService.postMeme(memePostDto));
    }
    
    @DeleteMapping("/all")
        public ResponseEntity<Void> deleteAll(){
            memeService.deleteAll();
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemeById(@PathVariable String id) {
        try {
            memeService.deleteMemeById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}