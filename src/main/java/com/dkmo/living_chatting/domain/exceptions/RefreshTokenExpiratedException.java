package com.dkmo.living_chatting.domain.exceptions;

public class RefreshTokenExpiratedException extends ModelExceptions{

public RefreshTokenExpiratedException(){
    super("Token is expirated");
  }
  
}
