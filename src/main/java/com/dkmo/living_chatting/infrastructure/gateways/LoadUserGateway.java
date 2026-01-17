package com.dkmo.living_chatting.infrastructure.gateways;

import com.dkmo.living_chatting.application.gateway.LoginPolicyGateway;
import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;
import com.dkmo.living_chatting.infrastructure.repositories.UsersRepository;

public class LoadUserGateway implements LoginPolicyGateway{
private final  UserEntityMapper  userEntityMapper;
private final UsersRepository usersRepository;

  /**
   * @param loginPolicyInteracor
   */
  public LoadUserGateway(UserEntityMapper userEntityMapper,UsersRepository usersRepository) {
    this.userEntityMapper = userEntityMapper;
    this.usersRepository = usersRepository;
  }
public User findByEmail(String email){
    UserEntity userEntity = usersRepository.findByEmail(email);
    return userEntity != null ? userEntityMapper.toDomain(userEntity) : null;
  }
}
