package com.dkmo.living_chatting.application.exceptions;

public class NotificationUnauthorizedException extends RuntimeException {
  public NotificationUnauthorizedException(){
    super("Notificação não autorizada");
  }

}

