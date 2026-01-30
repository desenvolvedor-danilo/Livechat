package com.dkmo.living_chatting.domain.model;

import java.time.Instant;
import java.util.List;

public class Conversation {
    
  private String id;
  private List<String> participantes;
  private String lastMessage;
  private String lastSender;
  private String name;
  private Instant updatedAt;
  

  /**
   * @param id
   * @param participantes
   * @param lastMessage
   * @param lastSender
   * @param name
   * @param updatedAt
   */
  private Conversation(String id, List<String> participantes, String lastMessage, String lastSender, String name,
      Instant updatedAt) {
    this.id = id;
    this.participantes = participantes;
    this.lastMessage = lastMessage;
    this.lastSender = lastSender;
    this.name = name;
    this.updatedAt = updatedAt;
  }
  public static Conversation create(String id, List<String> participantes, String lastMessage, String lastSender, String name,
      Instant updatedAt){
    return new Conversation(id, participantes, lastMessage, lastSender, name, updatedAt);
  }
  /**
   * @return the id
   */
  public String  getId() {
    return id;
  }
  /**
   * @return the participantes
   */
  public List<String> getParticipantes() {
    return participantes;
  }
  /**
   * @return the lastMessage
   */
  public String getLastMessage() {
    return lastMessage;
  }
  /**
   * @return the lastSender
   */
  public String getLastSender() {
    return lastSender;
  }
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  /**
   * @return the updatedAt
   */
  public Instant getUpdatedAt() {
    return updatedAt;
  }

}
