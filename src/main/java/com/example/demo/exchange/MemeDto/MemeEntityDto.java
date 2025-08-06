package com.example.demo.exchange.MemeDto;


import java.util.List;

import javax.xml.stream.events.Comment;

import com.example.demo.Model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemeEntityDto {

  
  private String id;

  private String ussername;

  private String title;

  private String url;

  private String subtitle;



}
