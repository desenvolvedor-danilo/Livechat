package com.dkmo.living_chatting.controller.DTOs;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.dkmo.living_chatting.domain.model.Conversation;

public record ConversationResponseDto(String id,String message, List<String> participantes, String updatedAt,String name) {
   public static ConversationResponseDto create(Conversation conversation){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.of("America/Sao_Paulo")); 
    return new ConversationResponseDto(conversation.getId(), conversation.getLastMessage(), conversation.getParticipantes(),formatter.format(conversation.getUpdatedAt()),conversation.getName());
  } 
}
