package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.exceptions.UserNotFoundExceptions;
import com.dkmo.living_chatting.application.gateway.AuthorizationGateway;
import com.dkmo.living_chatting.application.gateway.FindUserGateway;
import com.dkmo.living_chatting.domain.model.AbstractAuthorization;
import com.dkmo.living_chatting.domain.model.User;

public class GenerateTokenUseCase {
private final AuthorizationGateway authorizationGateway;
private final FindUserGateway findUserGateway;
public GenerateTokenUseCase(AuthorizationGateway authorizationGateway, FindUserGateway findUserGateway) {
  this.authorizationGateway = authorizationGateway;
  this.findUserGateway = findUserGateway;
}

public AbstractAuthorization execute(String email){
    User user = findUserGateway.findByEmail(email);
    if(user==null){
      throw new UserNotFoundExceptions();
    }
    AbstractAuthorization abstractAuthorization = 
    authorizationGateway.generateToken(user);
    return abstractAuthorization;
  }
}
