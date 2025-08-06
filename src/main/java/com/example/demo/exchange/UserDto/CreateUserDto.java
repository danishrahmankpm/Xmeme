package com.example.demo.exchange.UserDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserDto {
    @NotBlank
    String username;
    @NotBlank
    String password;
    @NotBlank    
    String name;
    @NotBlank
    String email;

}
