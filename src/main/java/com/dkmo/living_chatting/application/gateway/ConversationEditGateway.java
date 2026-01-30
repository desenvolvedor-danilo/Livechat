package com.dkmo.living_chatting.application.gateway;

import com.dkmo.living_chatting.domain.model.Conversation;
import com.dkmo.living_chatting.domain.model.Message;

public interface ConversationEditGateway {
void editConversation(Conversation conversation,Message message);    
}
