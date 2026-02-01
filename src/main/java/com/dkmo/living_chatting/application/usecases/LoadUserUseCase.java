package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.exceptions.UserNotFoundExceptions;
import com.dkmo.living_chatting.application.gateway.LoadUserGateway;
import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.domain.model.UsersReference;

public class LoadUserUseCase {
private final LoadUserGateway loadUserGateway;

/**
 * @param loadUserGateway
 */
public LoadUserUseCase(LoadUserGateway loadUserGateway) {
  this.loadUserGateway = loadUserGateway;
}  
  public UsersReference execute(String sender){
    User user = loadUserGateway.findUserByEmail(sender);
    if(user == null){ throw new UserNotFoundExceptions();
    }
    return new UsersReference(user.nome(),user.getFileReference().url());
  }
}
