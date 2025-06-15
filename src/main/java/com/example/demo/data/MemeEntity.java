package com.example.demo.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemeEntity {

  
  private String id;

  private String name;

  private String url;

  private String caption;

}
