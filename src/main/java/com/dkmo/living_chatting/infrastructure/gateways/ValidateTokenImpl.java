package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dkmo.living_chatting.application.gateway.ValidateTokenGateway;

@Component
public class ValidateTokenImpl implements ValidateTokenGateway {

  @Value("key.auth.token")
  private String key;


  @Override
  public String validateToken(String token) {
  try{
  Algorithm algorithm = Algorithm.HMAC256(key);
  return JWT.require(algorithm).withIssuer("living_chatting")
  .build()
  .verify(token)
  .getSubject();
  }catch(JWTVerificationException e){
      return "";
    }
  }

  
}
