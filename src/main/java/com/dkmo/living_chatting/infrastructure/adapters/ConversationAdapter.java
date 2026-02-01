package com.dkmo.living_chatting.infrastructure.adapters;

import com.dkmo.living_chatting.domain.model.Conversation;
import com.dkmo.living_chatting.infrastructure.persistence.ConversationEntity;

public class ConversationAdapter {
public static ConversationEntity toConversationEntity(Conversation conversation){
return new ConversationEntity(conversation.getId(),conversation.getParticipantes(),conversation.getLastMessage(),conversation.getLastSender(),conversation.getName(),conversation.getUpdatedAt());
  }
  public Conversation toConversation(ConversationEntity conversationEntity){
    return Conversation.create(conversationEntity.getId(), conversationEntity.getParticipantes(), conversationEntity.getLastMessage(), conversationEntity.getName(), conversationEntity.getName(), conversationEntity.getUpdatedAt());
  }
}
