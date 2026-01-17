package com.dkmo.living_chatting.infrastructure.adapters;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.dkmo.living_chatting.domain.model.Message;

public record MessageToResponse(String message,String
from, String to,String createdAt,String user,List<String>participants) {
  public static MessageToResponse send(Message message){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm")
    .withZone(ZoneId.of("America/Sao_Paulo"));
    return new MessageToResponse(message.getMessage(),message.getFrom(),message.getTo(),formatter.format(message.getNow()),message.getUser(),message.getParticipants());
  }
    
}
