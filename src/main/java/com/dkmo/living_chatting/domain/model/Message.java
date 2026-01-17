package com.dkmo.living_chatting.domain.model;

import java.time.Instant;
import java.util.List;
import com.dkmo.living_chatting.domain.exceptions.InvalidRecipientException;

public class Message{
  private String message;
  private String from;
  private String to;
  private Instant now;
  private String user;
  // private String sender;
  private List<String> participants;
   
  
/**
   * @param message
   * @param from
   * @param to
   * @param now
   */

  public static Message create(String message, String from, String to, Instant now,String user) {
    return new Message(message,from,to,now,user);
  }

/**
 * @param message
 * @param from
 * @param to
 * @param now
 *
 */
private Message(String message, String from, String to, Instant now, String user) {
        if(to==null){
            throw new InvalidRecipientException();  
           }
  this.message = message;
  this.from = from;
  this.to = to;
  this.now = now;
  this.user = user;
  this.participants = List.of(from,to);

}

// public void MarkAsCreatedAt(Instant instant){
//     this.now = instant;
//   }
// public void defineSender(String sender){
//     this.sender = sender;
//   }

/**
 * @return the message
 */
public String getMessage() {
  return message;
}

/**
 * @return the from
 */
public String getFrom() {
  return from;
}

/**
 * @return the to
 */
public String getTo() {
  return to;
}

/**
 * @return the now
 */
public Instant getNow() {
  return now;
}

/*
 * @return the user
 */
public String getUser() {
  return user;
}

/**
 * @return the sender
 */
// public String getSender() {
//   return sender;
// }

/**
 * @return the participants
 */
public List<String> getParticipants() {
  return participants;
}

}
