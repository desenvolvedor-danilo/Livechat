package com.dkmo.living_chatting.application.gateway;

import com.dkmo.living_chatting.domain.model.Conversation;
import com.dkmo.living_chatting.domain.model.Message;

public interface ConversationCreateGateway {
Conversation createConversation(Message message);    
}
