package com.dkmo.living_chatting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection = "Conversas")
public class Conversation {
@Id
  private String id;
  private List<String> participantes = new ArrayList<>();
  private String lastMessage;
  private String lastSender;
  private String name;
  private String updatedAt;
  public void addParticipantes(String from,String to){
    this.participantes.add(from);
    this.participantes.add(to);
  }
}

