package com.dkmo.living_chatting.infrastructure.gateways;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.ConversationGateway;
import com.dkmo.living_chatting.domain.model.Conversation;
import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.infrastructure.adapters.ConversationAdapter;
import com.dkmo.living_chatting.infrastructure.persistence.ConversationEntity;
import com.dkmo.living_chatting.infrastructure.repositories.ConversationRepository;
@Component
public class FindConversation implements ConversationGateway {
@Autowired
  private ConversationRepository conversationRepository;
  @Override
  public Conversation findConversation(Message message) {
    try{
  Optional<ConversationEntity> optional = conversationRepository.findById(message.getIdConversa());
   return ConversationAdapter.toConversation(optional.get());
   }catch(Exception e){
      return null;
    }
  } 
}
