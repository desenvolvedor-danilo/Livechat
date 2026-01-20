package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.MessageSaveGateway;
import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.infrastructure.adapters.MessagesPrivateMappers;
// import com.dkmo.living_chatting.infrastructure.adapters.MessagesPrivateMappers;
import com.dkmo.living_chatting.infrastructure.persistence.MessagesPrivate;
import com.dkmo.living_chatting.infrastructure.repositories.MessagePrivateRepository;
@Component 
public class SaveMessages implements MessageSaveGateway {
  @Autowired
  private MessagePrivateRepository messagePrivateRepository;
  private final MessagesPrivateMappers messagesPrivateMappers;
  public SaveMessages(MessagesPrivateMappers messagesPrivateMappers){
    this.messagesPrivateMappers = messagesPrivateMappers;

  }


  @Override
  public void saveMessage(Message message) {
    MessagesPrivate messagesPrivate = messagesPrivateMappers.toMessagePrivate(message);
   messagePrivateRepository.save(messagesPrivate);
  }
    
}
