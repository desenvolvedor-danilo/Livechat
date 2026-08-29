package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.DeleteHashByIdGateway;
import com.dkmo.living_chatting.infrastructure.repositories.CodeVerifyRepository;

@Component
public class DeleteHashCode implements DeleteHashByIdGateway {
  @Autowired
  private CodeVerifyRepository codeVerifyRepository;

  @Override
  public void deleteHashCode(String id) {
    codeVerifyRepository.deleteById(id);
  }

}
