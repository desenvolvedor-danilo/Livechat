package com.dkmo.living_chatting.infrastructure.gateways;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.LoadAllUsersGateway;
import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;
import com.dkmo.living_chatting.infrastructure.repositories.UsersRepository;

@Component
public class LoadAllUsers implements LoadAllUsersGateway {
@Autowired
  private UsersRepository usersRepository;

@Override
public List<User> findAllUsers() {
List<UserEntity> userEntity = usersRepository.findAll();
  return userEntity.stream().map(UserEntity::toDomain).toList();
}
}
