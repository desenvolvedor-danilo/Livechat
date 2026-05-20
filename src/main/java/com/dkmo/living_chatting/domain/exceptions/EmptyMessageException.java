package com.dkmo.living_chatting.domain.exceptions;

public class EmptyMessageException extends ModelExceptions {
public EmptyMessageException(){
    super("UrlFile and Message cannot to be empty");
  }
}
