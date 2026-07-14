package com.dkmo.living_chatting.infrastructure.gateways;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.DeleteMessageCallbackGateway;
import com.dkmo.living_chatting.infrastructure.dto.MessageDeleteEvent;

@Component
public class deleteMessageCallbackImpl implements
    DeleteMessageCallbackGateway {

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @Override
  public void deleteMessageCallback(String destinatario, UUID id) {
    simpMessagingTemplate.convertAndSendToUser(destinatario, "/queue/message",
        new MessageDeleteEvent("MESSAGE_DELETE", id.toString()));
  }

}
