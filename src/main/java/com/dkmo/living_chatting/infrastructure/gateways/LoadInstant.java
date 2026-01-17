package com.dkmo.living_chatting.infrastructure.gateways;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.InstantGateway;
@Component
public class LoadInstant implements InstantGateway{
  @Override
  public Instant now() {
    return Instant.now();
  }
    
}
