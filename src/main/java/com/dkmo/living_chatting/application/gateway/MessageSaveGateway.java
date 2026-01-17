package com.dkmo.living_chatting.application.gateway;

import com.dkmo.living_chatting.domain.model.Message;

public interface MessageSaveGateway {
void saveMessage(Message message);    
}
