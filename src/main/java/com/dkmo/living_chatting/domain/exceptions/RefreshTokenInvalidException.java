package com.dkmo.living_chatting.domain.exceptions;

public class RefreshTokenInvalidException extends ModelExceptions {
public RefreshTokenInvalidException(){
    super("Refresh token is not valid");
  }
  
}
