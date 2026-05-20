package com.dkmo.living_chatting.infrastructure.gateways;

import java.security.MessageDigest;

import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.GenerateHashGateway;
@Component
public class GenerateHashImpl implements GenerateHashGateway {

  @Override
  public String generateHash(String value) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte [] hashBytes = md.digest(value.getBytes());
      StringBuilder hex = new StringBuilder();
      for(byte b : hashBytes){
        hex.append(String.format("%02x", b));
      }
      return hex.toString();
    } catch (Exception e) {
    throw new RuntimeException(e);
    }
  }

  
}
