package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.ConversationCreateGateway;
import com.dkmo.living_chatting.domain.model.Conversation;
import com.dkmo.living_chatting.domain.model.Message;
@Component
public class CreateConversation implements ConversationCreateGateway{

  @Override
  public Conversation createConversation(Message message) {
  return Conversation.create(message.getIdConversa(),message.getParticipants(),message.getMessage(),message.getFrom(),message.getUser(),message.getNow());
  }  
}
