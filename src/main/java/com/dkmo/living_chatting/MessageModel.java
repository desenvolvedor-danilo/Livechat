package com.dkmo.living_chatting;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection = "messages")
public class MessageModel {
  @Id
  private String id;
  private String email;
  private String timeStamp;
  private String username;
  private String message; 
}
