package com.dkmo.living_chatting.application.usecases;

import java.util.List;

import com.dkmo.living_chatting.application.gateway.LoadNotificationGateway;
import com.dkmo.living_chatting.domain.model.Conversation;

public class LoadNotificationsUseCase {
  private final LoadNotificationGateway loadNotificationGateway;

/**
   * @param loadNotificationGateway
   */
  public LoadNotificationsUseCase(LoadNotificationGateway loadNotificationGateway) {
    this.loadNotificationGateway = loadNotificationGateway;
  }

public List<Conversation> loadConversation(String sender){
return loadNotificationGateway.loadConversation(sender); 
  }
}
