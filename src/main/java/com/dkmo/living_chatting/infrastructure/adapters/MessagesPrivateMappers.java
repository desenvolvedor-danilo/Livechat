package com.dkmo.living_chatting.infrastructure.adapters;



import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.infrastructure.persistence.MessagesPrivate;
@Component
public class MessagesPrivateMappers { 
public  MessagesPrivate toMessagePrivate(Message message){
  return new MessagesPrivate(message.getId().toString(), message.getTo(), message.getFrom(), message.getMessage(), message.getNow(), message.getUser(),null, message.getUrlFile()!=null?message.getUrlFile():null,message.getParticipants(),message.getIdConversa());
  }
}
