package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.demo.Model.MemeModel;
import com.example.demo.exchange.MemeDto.MemeEntityDto;
import com.example.demo.repository.MemeRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class MemeServiceTest {

    @InjectMocks
    private MemeService memeService;

    @Mock
    private MemeRepositoryService memeRepositoryService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testGetMemeFeed() {
       
        MemeModel memeModel1 = new MemeModel("1", "http://image.com/1.jpg", "funny meme","Rakesh");
        MemeModel memeModel2 = new MemeModel("2", "http://image.com/2.jpg", "another meme","Sulaiman");
        List<MemeModel> mockModels = List.of(memeModel1, memeModel2);

        MemeEntityDto entity1 = new MemeEntityDto("1", "Rakesh", "http://image.com/1.jpg","funny meme");
        MemeEntityDto entity2 = new MemeEntityDto("2", "Sulaiman", "http://image.com/2.jpg","another meme");

        // Mock repo call
        when(memeRepositoryService.getMemefeed()).thenReturn(mockModels);

        // Mock mapping
        when(modelMapper.map(memeModel1, MemeEntityDto.class)).thenReturn(entity1);
        when(modelMapper.map(memeModel2, MemeEntityDto.class)).thenReturn(entity2);

        // Actual call
        ResponseFeedDto response = memeService.getMemeFeed();

        // Assert
        List<MemeEntityDto> result = (List<MemeEntityDto>) response.getMemes(); 
        assertEquals(2, result.size());
        assertEquals("funny meme", result.get(0).getCaption());

        verify(memeRepositoryService, times(1)).getMemefeed();
        verify(modelMapper, times(2)).map(any(MemeModel.class), eq(MemeEntityDto.class));
    }
}
