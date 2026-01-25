package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.gateway.LoginPolicyGateway;
import com.dkmo.living_chatting.domain.model.FileReference;
import com.dkmo.living_chatting.domain.model.User;

public class GetPhotoProfileUseCase {
  private final LoginPolicyGateway loginPolicyGateway;
  public GetPhotoProfileUseCase(LoginPolicyGateway loginPolicyGateway){
  this.loginPolicyGateway = loginPolicyGateway;
  }
public FileReference getPhotoProfile(GetPhotoProfileInput input){
User user = loginPolicyGateway.findByEmail(input.email());
    return user.getFileReference();
  }     
}
