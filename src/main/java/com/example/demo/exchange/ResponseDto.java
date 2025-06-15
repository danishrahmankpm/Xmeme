package com.example.demo.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;
import com.example.demo.data.MemeEntity;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class ResponseDto {

  private List<MemeEntity> memes;


  
}
