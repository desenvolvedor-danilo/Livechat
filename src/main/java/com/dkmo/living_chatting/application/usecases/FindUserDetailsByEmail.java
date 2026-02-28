package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.gateway.FindUserGateway;
import com.dkmo.living_chatting.domain.model.User;

public class FindUserDetailsByEmail {
private final FindUserGateway findUserGateway;

public FindUserDetailsByEmail(FindUserGateway findUserGateway) {
    this.findUserGateway = findUserGateway;
}
  public User execute(String username){
    return findUserGateway.findByEmail(username);
  }
}
