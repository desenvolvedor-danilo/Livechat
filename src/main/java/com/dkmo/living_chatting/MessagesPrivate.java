package com.dkmo.living_chatting;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MessagesPrivate {
  private String id;
  private String to;
  private String from;
  private String message;
 
  public MessagesPrivate(String id, String to, String from, String message) {
    this.id = id;
    this.to = to;
    this.from = from;
    this.message = message;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
