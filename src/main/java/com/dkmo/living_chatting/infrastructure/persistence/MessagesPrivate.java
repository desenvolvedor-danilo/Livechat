package com.dkmo.living_chatting.infrastructure.persistence;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dkmo.living_chatting.domain.model.Message;

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
  private Instant timeStamp;
  private String user;
  private String  url;
  //private long count;
  private String name;
  private List<String> participantes;
  /**
   * @param id
   * @param to
   * @param from
   * @param message
   * @param timeStamp
   */
  public MessagesPrivate(String id, String to, String from, String message, Instant timeStamp,String user,String name,String url,List<String> participantes,String idConversa) {
    this.id = id;
    this.to = to;
    this.from = from;
    this.message = message;
    this.timeStamp = timeStamp;
    this.user = user;
    this.name = name;
    this.url = url;
    this.participantes = participantes;
    this.idConversa = idConversa;

  }
public Message toDomain(){
  Message messageDomain = Message.create(message, from, to, timeStamp, user,idConversa);
  return messageDomain;
  }
}

