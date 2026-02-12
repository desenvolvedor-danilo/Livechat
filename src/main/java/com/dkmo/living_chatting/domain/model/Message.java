package com.dkmo.living_chatting.domain.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.dkmo.living_chatting.domain.exceptions.EmptyMessageException;
import com.dkmo.living_chatting.domain.exceptions.InvalidRecipientException;

public class Message{
  private UUID id;
  /**
   * @return the id
   */
  public UUID getId() {
    return id;
  }
  private String idConversa;
  private String message;
  private String from;
  private String to;
  private Instant now;
  private String user;
  private String urlFile;
  private List<String> participants;
   
  public static Message create(UUID id, String message, String from, String to, Instant now,String user,String idConversa,String urlFile) {
    boolean temTexto = message != null && !message.isBlank();
    boolean temArquivo = urlFile != null && !urlFile.isBlank();
   if(!temTexto && !temArquivo){
      throw new EmptyMessageException();
    } 
   return new Message(id, message,from,to,now,user,idConversa,urlFile);
  }

private Message(UUID
    id, String message, String from, String to, Instant now, String user,String idConversa,String urlFile) {
        if(to==null){
            throw new InvalidRecipientException();  
           }
  this.id = id;
  this.message = message;
  this.from = from;
  this.to = to;
  this.now = now;
  this.user = user;
  this.participants = List.of(from,to);
  this.idConversa = idConversa;
  this.urlFile = urlFile;
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
public void defineUrlFile(String urlFile){
    this.urlFile = urlFile;
  }

}
