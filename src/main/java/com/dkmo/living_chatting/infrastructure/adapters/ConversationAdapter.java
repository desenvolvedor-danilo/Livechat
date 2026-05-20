package com.dkmo.living_chatting.infrastructure.adapters;

import com.dkmo.living_chatting.domain.model.Conversation;
import com.dkmo.living_chatting.infrastructure.persistence.ConversationEntity;

public class ConversationAdapter {
public static ConversationEntity toConversationEntity(Conversation conversation){
return new ConversationEntity(conversation.getId(),conversation.getParticipantes(),conversation.getLastMessage(),conversation.getLastSender(),conversation.getName(),conversation.getUpdatedAt(),conversation.getRecipient());
  }
  public Conversation toConversation(ConversationEntity conversationEntity){
    Conversation conversation = Conversation.create(conversationEntity.getId(), conversationEntity.getParticipantes(), conversationEntity.getLastMessage(), conversationEntity.getName(),conversationEntity.getLastSender(), conversationEntity.getUpdatedAt());
    conversation.defineRecipient(conversationEntity.getRecipient());
    return conversation;
    
  }
}
