package com.example.demo.controller;

import com.example.demo.data.MemeEntity;
import com.example.demo.exchange.MemePostDto;
import com.example.demo.exchange.PostResponseDto;
import com.example.demo.service.MemeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.mongodb.embedded.version=4.0.2")
public class MemeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemeService memeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetMemes() throws Exception {
        MemeEntity meme = new MemeEntity("1", "Rakesh", "http://example.com/meme.jpg", "funny");
        when(memeService.getMemeFeed()).thenReturn(new com.example.demo.exchange.ResponseDto(List.of(meme)));

        mockMvc.perform(get("/memes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Rakesh"))
                .andExpect(jsonPath("$[0].url").value("http://example.com/meme.jpg"))
                .andExpect(jsonPath("$[0].caption").value("funny"));
    }

    @Test
    void testGetMemeByIdFound() throws Exception {
        MemeEntity meme = new MemeEntity("2", "Sulaiman", "http://example.com/another.jpg", "lol");
        when(memeService.getMemeById("2")).thenReturn(new com.example.demo.exchange.ResponseDto(List.of(meme)));

        mockMvc.perform(get("/memes/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.name").value("Sulaiman"))
                .andExpect(jsonPath("$.url").value("http://example.com/another.jpg"))
                .andExpect(jsonPath("$.caption").value("lol"));
    }

    @Test
    void testPostMeme() throws Exception {
        MemePostDto memeRequestDto = new MemePostDto("George", "caption", "http://example.com/3.jpg");
        PostResponseDto response = new PostResponseDto("1");

        when(memeService.postMeme(memeRequestDto)).thenReturn(response);

        mockMvc.perform(post("/memes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memeRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }
}
