package com.dkmo.living_chatting.infrastructure.adapters;


import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.infrastructure.persistence.MessagesPrivate;
@Component
public class MessagesPrivateMappers { 
public  MessagesPrivate toMessagePrivate(Message message){
  return new MessagesPrivate(null, message.getTo(), message.getFrom(), message.getMessage(), message.getNow(), message.getUser(), null, null,message.getParticipants(),message.getIdConversa());
  }
}
