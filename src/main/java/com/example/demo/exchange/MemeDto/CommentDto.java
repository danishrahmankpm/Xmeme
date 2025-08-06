package com.example.demo.exchange.MemeDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    @NotBlank
    private String id;
    @NotBlank    
    private String username;
    @NotBlank
    private String comment;

  
}
