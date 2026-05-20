package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.DeleteRefreshTokenGateway;
import com.dkmo.living_chatting.infrastructure.repositories.RefreshTokenRepository;
@Component
public class DeleteHashRefreshToken implements DeleteRefreshTokenGateway {
@Autowired
  private RefreshTokenRepository refreshTokenRepository;
  @Override
  public void deleteHashRefreshToken(String hash) {
  refreshTokenRepository.deleteByHashToken(hash); 
  }

  
}
