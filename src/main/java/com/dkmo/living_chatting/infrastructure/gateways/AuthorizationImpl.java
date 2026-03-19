package com.dkmo.living_chatting.infrastructure.gateways;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.dkmo.living_chatting.application.gateway.AuthorizationGateway;
import com.dkmo.living_chatting.domain.model.AbstractAuthorization;
import com.dkmo.living_chatting.domain.model.User;

@Component
public class AuthorizationImpl implements AuthorizationGateway {
@Value("key.auth.token")
 private String key;
  @Override
  public AbstractAuthorization generateToken(User user) {
try{
  Algorithm algorithm = Algorithm.HMAC256(key);
   String token = JWT.create().withIssuer("living_chatting")
  .withSubject(user.email())
  .withExpiresAt(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00")))
  .sign(algorithm);


   String refreshToken = JWT.create().withIssuer("living_chatting")
  .withSubject(user.email())
  .withExpiresAt(LocalDateTime.now().plusHours(720).toInstant(ZoneOffset.of("-03:00")))
  .sign(algorithm); 

  return new AbstractAuthorization(token,refreshToken);
  }catch(JWTCreationException e){
   throw new RuntimeException("Error to the create token: "+e.getMessage());
  }
  }
}
