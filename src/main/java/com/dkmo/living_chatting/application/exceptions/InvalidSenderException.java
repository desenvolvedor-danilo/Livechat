package com.dkmo.living_chatting.application.exceptions;

public class InvalidSenderException extends RuntimeException{
  public InvalidSenderException(){
    super("Invalid Destination"); 
  }    
}
