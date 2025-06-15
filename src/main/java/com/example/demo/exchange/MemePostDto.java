package com.example.demo.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;




@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemePostDto {

    @NotBlank
    private String name;

    @NotBlank
    private String caption;

    @NotBlank
    private String url;
    
}
