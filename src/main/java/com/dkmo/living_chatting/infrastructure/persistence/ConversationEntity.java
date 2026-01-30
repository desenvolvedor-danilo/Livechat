package com.dkmo.living_chatting.infrastructure.persistence;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection = "Conversas")
public class ConversationEntity {
@Id
  private String id;
  private List<String> participantes = new ArrayList<>();
  private String lastMessage;
  private String lastSender;
  private String name;
  private Instant updatedAt;
  public void addParticipantes(String from,String to){
    this.participantes.add(from);
    this.participantes.add(to);
  }
  /**
   * @param id
   * @param participantes
   * @param lastMessage
   * @param lastSender
   * @param name
   * @param updatedAt
   */
  public ConversationEntity(String id, List<String> participantes, String lastMessage, String lastSender, String name,
      Instant updatedAt) {
    this.id = id;
    this.participantes = participantes;
    this.lastMessage = lastMessage;
    this.lastSender = lastSender;
    this.name = name;
    this.updatedAt = updatedAt;
  }

}

