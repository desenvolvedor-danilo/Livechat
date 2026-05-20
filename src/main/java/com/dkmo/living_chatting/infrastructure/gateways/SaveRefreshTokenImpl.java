package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.SaveRefreshTokenGateway;
import com.dkmo.living_chatting.domain.model.RefreshToken;
import com.dkmo.living_chatting.infrastructure.adapters.RefreshTokenMappers;
import com.dkmo.living_chatting.infrastructure.repositories.RefreshTokenRepository;
@Component
public class SaveRefreshTokenImpl implements SaveRefreshTokenGateway {
  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Override
  public void save(RefreshToken refreshToken) {
    refreshTokenRepository.save(RefreshTokenMappers.refreshTokenToRefreshTokenEntity(refreshToken));
   
  }

  
}
