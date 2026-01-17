package com.dkmo.living_chatting.infrastructure.gateways;

import com.dkmo.living_chatting.application.gateway.UserGateway;
import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;
import com.dkmo.living_chatting.infrastructure.repositories.UsersRepository;

public class UserRepositoryGateway implements UserGateway{
private final UsersRepository usersRepository;
private final UserEntityMapper userEntityMapper;
  public UserRepositoryGateway(UsersRepository usersRepository,UserEntityMapper userEntityMapper){
    this.usersRepository = usersRepository;
    this.userEntityMapper = userEntityMapper;
  }
  @Override
  public User createUser(User user) {
  UserEntity userEntity = userEntityMapper.toEntity(user); 
  UserEntity objSaved = usersRepository.save(userEntity);
    return userEntityMapper.toDomain(objSaved);
  }  
}
