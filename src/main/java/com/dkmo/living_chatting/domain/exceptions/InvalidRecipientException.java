package com.dkmo.living_chatting.domain.exceptions;

public class InvalidRecipientException extends RuntimeException{
  public InvalidRecipientException(){
    super("recipient cannot be null");
  }
    
}
