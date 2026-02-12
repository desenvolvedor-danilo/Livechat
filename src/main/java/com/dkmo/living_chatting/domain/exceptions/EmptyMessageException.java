package com.dkmo.living_chatting.domain.exceptions;

public class EmptyMessageException extends RuntimeException {
public EmptyMessageException(){
    super("UrlFile and Message cannot to be empty");
  }
}
