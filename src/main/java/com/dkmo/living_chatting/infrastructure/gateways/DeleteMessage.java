package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.DeleteMessageGateway;
import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.infrastructure.adapters.MessagesPrivateMappers;
import com.dkmo.living_chatting.infrastructure.repositories.MessagePrivateRepository;

@Component
public class DeleteMessage implements DeleteMessageGateway {

  @Autowired
  private MessagePrivateRepository messagePrivateRepository;

  @Override
  public void deleteMessage(Message message) {
    messagePrivateRepository.delete(new MessagesPrivateMappers().toMessagePrivate(message));
  }

}
