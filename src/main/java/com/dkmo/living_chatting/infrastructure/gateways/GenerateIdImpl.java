package com.dkmo.living_chatting.infrastructure.gateways;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.GenerateIdGateway;
@Component
public class GenerateIdImpl implements GenerateIdGateway {

  @Override
  public UUID generateId() {
    return UUID.randomUUID();
  }
   
}
