package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.gateway.FindUserGateway;
import com.dkmo.living_chatting.domain.model.FileReference;
import com.dkmo.living_chatting.domain.model.User;

public class GetPhotoProfileUseCase {
  private final FindUserGateway loginPolicyGateway;
  public GetPhotoProfileUseCase(FindUserGateway loginPolicyGateway){
  this.loginPolicyGateway = loginPolicyGateway;
  }
public FileReference getPhotoProfile(GetPhotoProfileInput input){
User user = loginPolicyGateway.findByEmail(input.email());
    return user.getFileReference();
  }     
}
