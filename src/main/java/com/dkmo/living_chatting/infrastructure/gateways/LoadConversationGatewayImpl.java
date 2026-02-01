package com.dkmo.living_chatting.infrastructure.gateways;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.LoadNotificationGateway;
import com.dkmo.living_chatting.domain.model.Conversation;
import com.dkmo.living_chatting.infrastructure.adapters.ConversationAdapter;
import com.dkmo.living_chatting.infrastructure.persistence.ConversationEntity;
import com.dkmo.living_chatting.infrastructure.repositories.ConversationRepository;
@Component
public class LoadConversationGatewayImpl implements LoadNotificationGateway{
@Autowired
  private ConversationRepository conversationRepository;
  @Override
  public List<Conversation> loadConversation(String from) {
   List<ConversationEntity> conversationEntity = conversationRepository.findByParticipantes(from);
   return conversationEntity.stream().map(new
   ConversationAdapter()::toConversation).toList();
  }
    
}
