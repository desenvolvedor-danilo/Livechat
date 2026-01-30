package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.ConversationSaveGateway;
import com.dkmo.living_chatting.domain.model.Conversation;
import com.dkmo.living_chatting.infrastructure.adapters.ConversationAdapter;
import com.dkmo.living_chatting.infrastructure.persistence.ConversationEntity;
import com.dkmo.living_chatting.infrastructure.repositories.ConversationRepository;
@Component
public class SaveConversation implements ConversationSaveGateway {
  @Autowired
  private ConversationRepository conversationRepository;


  @Override
  public void saveConversation(Conversation conversation) {
   ConversationEntity conversationEntity = ConversationAdapter.toConversationEntity(conversation);
    conversationRepository.save(conversationEntity);
  }
    
}
