package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.gateway.NotificationGateway;

public class NotificationUserUseCase {
private final NotificationGateway notificationGateway;

/**
 * @param notificationGateway
 */
public NotificationUserUseCase(NotificationGateway notificationGateway) {
  this.notificationGateway = notificationGateway;
}

public void execute(String tokenTarget,String title,String body,String email){
notificationGateway.sendNotification(tokenTarget,title,body,email);
  }    
}
