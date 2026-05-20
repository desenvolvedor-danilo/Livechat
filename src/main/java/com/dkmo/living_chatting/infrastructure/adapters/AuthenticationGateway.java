package com.dkmo.living_chatting.infrastructure.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.exceptions.InvalidCredentialsException;
@Component
public class AuthenticationGateway {

@Autowired
  private AuthenticationManager authenticationManager; 

  public Authentication authentication(String email, String password){
    try {
      return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(email, password));
    } catch (BadCredentialsException e) {
     throw new InvalidCredentialsException(); 
    }
  }
}
