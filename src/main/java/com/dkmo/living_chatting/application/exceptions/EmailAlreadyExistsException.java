package com.dkmo.living_chatting.application.exceptions;

public class EmailAlreadyExistsException extends ApplicationException {
  public EmailAlreadyExistsException() {
    super("The email is already registered");
  }
}
