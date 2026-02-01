package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.LoadUserGateway;
import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.infrastructure.adapters.UserEntityAdapter;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;
import com.dkmo.living_chatting.infrastructure.repositories.UsersRepository;
@Component
public class LoadUserGatewayImpl implements LoadUserGateway{
@Autowired
  private UsersRepository usersRepository;

  @Override
  public User findUserByEmail(String sender) {
    UserEntity userEntity = usersRepository.findByEmail(sender);
    return UserEntityAdapter.toUser(userEntity);
  }
    
}
