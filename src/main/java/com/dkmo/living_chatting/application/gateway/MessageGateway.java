package com.dkmo.living_chatting.application.gateway;

import com.dkmo.living_chatting.domain.model.Message;

public interface MessageGateway {
void sendMessage(Message message);    
}
