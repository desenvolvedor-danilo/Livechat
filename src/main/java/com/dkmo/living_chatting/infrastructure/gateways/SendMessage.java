package com.dkmo.living_chatting.infrastructure.gateways;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.dkmo.living_chatting.application.gateway.MessageGateway;
import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.infrastructure.adapters.MessageToResponse;

public class SendMessage implements MessageGateway {
  @Autowired 
  private final SimpMessagingTemplate simpMessagingTemplate;

  /**
   * @param simpMessagingTemplate
   */
  public SendMessage(SimpMessagingTemplate simpMessagingTemplate) {
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

  @Override
 public void sendMessage(Message message) {
    MessageToResponse AdapterMessage = MessageToResponse.send(message);
    simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/queue/message", AdapterMessage);  
  } 
     
} 
