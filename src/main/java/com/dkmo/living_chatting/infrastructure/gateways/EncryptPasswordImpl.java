package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.EncryptPasswordGateway;
@Component
public class EncryptPasswordImpl implements EncryptPasswordGateway {
  @Autowired
  private PasswordEncoder bCryptPasswordEncoder;

  @Override
  public String passwordEncrypt(String password) {
  return bCryptPasswordEncoder.encode(password);
  }
 
}
