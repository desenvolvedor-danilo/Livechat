package com.dkmo.living_chatting.domain.model;

import java.time.Instant;
import java.util.List;

import com.dkmo.living_chatting.domain.exceptions.InvalidRecipientException;

public class Message{
  private String idConversa;
  private String message;
  private String from;
  private String to;
  private Instant now;
  private String user;
  private String urlFile;
  //private CreateIdReference createIdReference;
  private List<String> participants;
   
  public static Message create(String message, String from, String to, Instant now,String user,String idConversa) {
    return new Message(message,from,to,now,user,idConversa);
  }

private Message(String message, String from, String to, Instant now, String user,String idConversa) {
        if(to==null){
            throw new InvalidRecipientException();  
           }
  this.message = message;
  this.from = from;
  this.to = to;
  this.now = now;
  this.user = user;
  this.participants = List.of(from,to);
  this.idConversa = idConversa;
}


public String getMessage() {
  return message;
}

public String getFrom() {
  return from;
}

public String getTo() {
  return to;
}

public Instant getNow() {
  return now;
}

public String getUser() {
  return user;
}

public List<String> getParticipants() {
  return participants;
}

public String getUrlFile() {
  return urlFile;
}

/**
 * @return the idConversa
 */
public String getIdConversa() {
  return idConversa;
}

}
