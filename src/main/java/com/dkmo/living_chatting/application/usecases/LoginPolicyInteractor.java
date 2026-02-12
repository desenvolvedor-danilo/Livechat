package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.exceptions.InvalidCredentialsException;
import com.dkmo.living_chatting.application.exceptions.UserNotFoundExceptions;
import com.dkmo.living_chatting.application.gateway.FindUserGateway;
import com.dkmo.living_chatting.domain.model.User;


public class LoginPolicyInteractor {
private final FindUserGateway loginPolicyGateway;
//private final ValidationGateway validationGateway;
/**
 * @param loginPolicyGateway
 */
public LoginPolicyInteractor(FindUserGateway loginPolicyGateway) {
  this.loginPolicyGateway = loginPolicyGateway;
 // this.validationGateway = validationGateway;
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
