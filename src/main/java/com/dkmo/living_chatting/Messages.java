package com.dkmo.living_chatting;

import lombok.Data;

@Data
public class Messages {
  private String to;
  private String from;
  private String message;
  private String urlFile;  
  private String user;
  private String timeStamp;
  private String name;
}
