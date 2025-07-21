package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "memes")
public class MemeModel {
    
    @Id
    private String id;

    private String userId; 
    
    private String url;
    
    private String caption;

    private String name;

    
}
