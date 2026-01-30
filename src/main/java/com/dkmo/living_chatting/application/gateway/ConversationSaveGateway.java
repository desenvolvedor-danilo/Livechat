package com.dkmo.living_chatting.application.gateway;

import com.dkmo.living_chatting.domain.model.Conversation;

public interface ConversationSaveGateway {
void saveConversation(Conversation conversation);    
}
