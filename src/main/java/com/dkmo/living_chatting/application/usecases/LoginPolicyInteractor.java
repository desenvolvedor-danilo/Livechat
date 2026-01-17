package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.exceptions.InvalidCredentialsException;
import com.dkmo.living_chatting.application.exceptions.UserNotFoundExceptions;
import com.dkmo.living_chatting.application.gateway.LoginPolicyGateway;
import com.dkmo.living_chatting.domain.model.User;


public class LoginPolicyInteractor {
private final LoginPolicyGateway loginPolicyGateway;

/**
 * @param loginPolicyGateway
 */
public LoginPolicyInteractor(LoginPolicyGateway loginPolicyGateway) {
  this.loginPolicyGateway = loginPolicyGateway;
}
public User execute(String email,String password)throws UserNotFoundExceptions,InvalidCredentialsException{
    User user = loginPolicyGateway.findByEmail(email);
    if(user==null){
      throw new UserNotFoundExceptions();
    }
    if(!user.senha().equals(password)){
      throw new InvalidCredentialsException();
    }
    return user;
  } 
}
