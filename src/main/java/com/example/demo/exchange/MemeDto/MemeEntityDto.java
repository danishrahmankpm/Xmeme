package com.example.demo.exchange.MemeDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemeEntityDto {

  
  private String id;

  private String name;

  private String url;

  private String caption;

}
