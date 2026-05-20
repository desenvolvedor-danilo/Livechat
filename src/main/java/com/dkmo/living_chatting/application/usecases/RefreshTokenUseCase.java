package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.gateway.AuthorizationGateway;
import com.dkmo.living_chatting.application.gateway.DeleteRefreshTokenGateway;
import com.dkmo.living_chatting.application.gateway.FindUserGateway;
import com.dkmo.living_chatting.application.gateway.GenerateCookieGateway;
import com.dkmo.living_chatting.application.gateway.GenerateHashGateway;
import com.dkmo.living_chatting.application.gateway.SaveRefreshTokenGateway;
import com.dkmo.living_chatting.application.gateway.ValidateTokenGateway;
import com.dkmo.living_chatting.domain.model.AbstractAuthorization;
import com.dkmo.living_chatting.domain.model.RefreshToken;
import com.dkmo.living_chatting.domain.model.User;

public class RefreshTokenUseCase {
private final SaveRefreshTokenGateway saveRefreshTokenGateway;
private final ValidateTokenGateway validateTokenGateway;
private final FindUserGateway findUserGateway;
private final AuthorizationGateway authorizationGateway;
private final GenerateCookieGateway generateCookieGateway;
private final GenerateHashGateway generateHashGateway;
private final DeleteRefreshTokenGateway deleteRefreshTokenGateway;

/**
 * @param refreshTokenGateway
 */
public RefreshTokenUseCase(ValidateTokenGateway validateTokenGateway,FindUserGateway findUserGateway,AuthorizationGateway authorizationGateway,GenerateCookieGateway generateCookieGateway,SaveRefreshTokenGateway saveRefreshTokenGateway,GenerateHashGateway generateHashGateway,DeleteRefreshTokenGateway deleteRefreshTokenGateway) {
  this.validateTokenGateway = validateTokenGateway; 
  this.findUserGateway = findUserGateway;
  this.authorizationGateway = authorizationGateway;
  this.generateCookieGateway = generateCookieGateway;
  this.saveRefreshTokenGateway = saveRefreshTokenGateway;
  this.generateHashGateway = generateHashGateway;
  this.deleteRefreshTokenGateway = deleteRefreshTokenGateway;
}
public void execute(String refreshToken){
   String email = validateTokenGateway.validateToken(refreshToken);
   if(!email.equals("")){
   User user = findUserGateway.findByEmail(email);
   AbstractAuthorization abstractAuthorization = authorizationGateway.generateToken(user);
   generateCookieGateway.write("token", abstractAuthorization.token());
  generateCookieGateway.write("refresh-token", abstractAuthorization.refreshToken());
    String hashRefreshToken = generateHashGateway.generateHash(abstractAuthorization.refreshToken());  
    RefreshToken refresh = RefreshToken.create(user.id(),hashRefreshToken,720); 
    if(refresh.matches(hashRefreshToken)){
        deleteRefreshTokenGateway.deleteHashRefreshToken(hashRefreshToken);
      }
    saveRefreshTokenGateway.save(refresh);

    }
  }
  
}
