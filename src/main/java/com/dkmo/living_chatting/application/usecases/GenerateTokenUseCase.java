package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.exceptions.UserNotFoundExceptions;
import com.dkmo.living_chatting.application.gateway.AuthorizationGateway;
import com.dkmo.living_chatting.application.gateway.FindUserGateway;
import com.dkmo.living_chatting.application.gateway.GenerateHashGateway;
import com.dkmo.living_chatting.application.gateway.SaveRefreshTokenGateway;
import com.dkmo.living_chatting.domain.model.AbstractAuthorization;
import com.dkmo.living_chatting.domain.model.RefreshToken;
import com.dkmo.living_chatting.domain.model.User;

public class GenerateTokenUseCase {
private final AuthorizationGateway authorizationGateway;
private final FindUserGateway findUserGateway;
private final SaveRefreshTokenGateway saveRefreshTokenGateway;
private final GenerateHashGateway generateHashGateway;
public GenerateTokenUseCase(AuthorizationGateway authorizationGateway, FindUserGateway findUserGateway,SaveRefreshTokenGateway saveRefreshTokenGateway,GenerateHashGateway generateHashGateway) {
  this.authorizationGateway = authorizationGateway;
  this.findUserGateway = findUserGateway;
  this.generateHashGateway = generateHashGateway;
  this.saveRefreshTokenGateway = saveRefreshTokenGateway;
}

public AbstractAuthorization execute(String email){
    User user = findUserGateway.findByEmail(email);
    if(user==null){
      throw new UserNotFoundExceptions();
    }
    AbstractAuthorization abstractAuthorization = 
    authorizationGateway.generateToken(user);
    String hashRefreshToken = generateHashGateway.generateHash(abstractAuthorization.refreshToken());
    RefreshToken refreshToken = RefreshToken.create(user.id(),hashRefreshToken,720);
    saveRefreshTokenGateway.save(refreshToken);
    return abstractAuthorization;
  }
}
