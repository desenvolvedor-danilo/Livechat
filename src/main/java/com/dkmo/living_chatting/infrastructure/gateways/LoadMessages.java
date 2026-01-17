package com.dkmo.living_chatting.infrastructure.gateways;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.LoadMessagesGateway;
import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.infrastructure.persistence.MessagesPrivate;
import com.dkmo.living_chatting.infrastructure.repositories.MessagePrivateRepository;
@Component
public class LoadMessages implements LoadMessagesGateway{
@Autowired
private MessagePrivateRepository messagePrivateRepository;
  @Override
  public List<Message> findMessagesBySender(String
  sender,String recipient) {
    List<MessagesPrivate> messagesPrivate = messagePrivateRepository.findByParticipantes(sender,recipient);
   return messagesPrivate.stream()
    .map(MessagesPrivate::toDomain).toList();
  } 
}
