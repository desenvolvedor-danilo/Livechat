package com.dkmo.living_chatting.application.exceptions;

public class NotFoundMessageException extends ApplicationException {
  public NotFoundMessageException() {
    super("Message not found");
  }
}
