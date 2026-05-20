package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.exceptions.InvalidCredentialsException;
import com.dkmo.living_chatting.application.gateway.ValidateTokenGateway;

public class ValidateTokenUseCase {
private final ValidateTokenGateway validateTokenGateway;

public ValidateTokenUseCase(ValidateTokenGateway validateTokenGateway) {
  this.validateTokenGateway = validateTokenGateway;
}
 
public String execute(String token){
    String tokenValidate = validateTokenGateway.validateToken(token);
    return tokenValidate;
  }

}
