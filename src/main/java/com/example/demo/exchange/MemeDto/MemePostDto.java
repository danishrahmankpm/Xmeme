package com.example.demo.exchange.MemeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;




@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemePostDto {
    @NotBlank
    private String username; 
    @NotBlank
    private String url;
    private String title;
    private String subtitle;
    
    
    
}
