package com.example.demo.controller;



import jakarta.validation.Valid;
import java.util.List;
import com.example.demo.data.MemeEntity;
import com.example.demo.exchange.MemePostDto;
import com.example.demo.exchange.PostResponseDto;
import com.example.demo.exchange.ResponseDto;
import com.example.demo.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/")
public class MemeController {

    @Autowired
    private MemeService memeService;

    @GetMapping("/memes")
    public List<MemeEntity> getMemes() {
        ResponseDto responseDto = memeService.getMemeFeed();
        List<MemeEntity> feed = responseDto.getMemes();

        
        
        return feed;
    }

    @GetMapping("/memes/{id}")
    public ResponseEntity<MemeEntity> getMemeById(@PathVariable String id) {
        ResponseDto response = memeService.getMemeById(id);

        if (response.getMemes().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }

        MemeEntity meme = response.getMemes().get(0);
        
        return ResponseEntity.ok(meme);
    }

    @PostMapping("/memes")
    public ResponseEntity<PostResponseDto> postMeme(@Valid @RequestBody MemePostDto memePostDto) {
        if (memePostDto == null || memePostDto.getName() == null || memePostDto.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(memeService.postMeme(memePostDto));
    }
    
    @DeleteMapping("/deleteall")
        public ResponseEntity<ResponseDto> deleteAll(){
            return ResponseEntity.ok(memeService.deleteAll());
    
    }
}