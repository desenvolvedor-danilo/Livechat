package com.dkmo.living_chatting.domain.exceptions;

public class InvalidRecipientException extends ModelExceptions{
  public InvalidRecipientException(){
    super("recipient cannot be null");
  }
    
}
