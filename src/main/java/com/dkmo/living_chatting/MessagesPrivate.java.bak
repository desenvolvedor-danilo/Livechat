package com.dkmo.living_chatting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data; 
@Data
@Document
public class MessagesPrivate {
  @Id
  private String id;
  private String idConversa;
  private String to;
  private String from;
  private String message;
  private String timeStamp;
  private String user;
  private String url;
  private long count;
  private String name;
  private List<String> participantes = new ArrayList<>();
  /**
   * @param id
   * @param to
   * @param from
   * @param message
   * @param timeStamp
   */
  public MessagesPrivate(String id, String to, String from, String message, String timeStamp,String user,String name,String url) {
    this.id = id;
    this.to = to;
    this.from = from;
    this.message = message;
    this.timeStamp = timeStamp;
    this.user = user;
    this.name = name;
    this.url = url;
    addParticipantes(); 
  }
private void addParticipantes(){
  this.participantes.add(this.from);
  this.participantes.add(this.to);
  }
}
