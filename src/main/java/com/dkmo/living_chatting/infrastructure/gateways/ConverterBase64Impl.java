package com.dkmo.living_chatting.infrastructure.gateways;

import java.util.Base64;

import com.dkmo.living_chatting.application.gateway.EncryptBase64Gateway;

public class ConverterBase64Impl implements EncryptBase64Gateway {

  @Override
  public String usernamePasswordEnconder(String rawPassword) {
    String token = new String(Base64.getEncoder().encode(rawPassword.getBytes()));
    return token;
    
}
}
