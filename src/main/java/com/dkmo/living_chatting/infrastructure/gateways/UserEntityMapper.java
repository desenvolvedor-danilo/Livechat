package com.dkmo.living_chatting.infrastructure.gateways;

import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;

public class UserEntityMapper {
UserEntity toEntity(User userDomainObject){
   return new UserEntity(userDomainObject.nome(),   userDomainObject.email(), userDomainObject.senha(),userDomainObject.usuario());
  }   
  User toDomain(UserEntity userEntity){
    return new User(userEntity.getName(),   userEntity.getEmail(),userEntity.getSenha(),userEntity.getUsuario());
  }
}
 
