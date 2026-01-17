package com.dkmo.living_chatting.application.usecases;

import java.util.List;

import com.dkmo.living_chatting.application.gateway.LoadAllUsersGateway;
import com.dkmo.living_chatting.domain.model.User;

public class LoadAllUsersUseCase {
  private final LoadAllUsersGateway loadAllUsersGateway;
  public LoadAllUsersUseCase(LoadAllUsersGateway loadAllUsersGateway){
    this.loadAllUsersGateway = loadAllUsersGateway;
  }
  public List<User> execute(){
return loadAllUsersGateway.findAllUsers();
  }
}
