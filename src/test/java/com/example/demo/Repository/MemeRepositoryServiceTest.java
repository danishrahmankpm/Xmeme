package com.example.demo.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.example.demo.Model.MemeModel;
import com.example.demo.repository.MemeRepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=4.0.2")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MemeRepositoryServiceTest {

    @Autowired
    private MemeRepositoryService memeRepositoryService;

    @BeforeEach
    void setup() {
        memeRepositoryService.deleteAll(); // Clear the database before each test
    }

    @Test
    void testSaveAndFindById() {
        MemeModel meme = new MemeModel("1", "http://url.com", "funny","Rakesh");
        memeRepositoryService.postMeme(meme);

        Optional<MemeModel> found = memeRepositoryService.getMemeById("1");
        assertTrue(found.isPresent(), "Meme should be present in DB");
        assertEquals("funny", found.get().getCaption(), "Caption should match");
    }
}
