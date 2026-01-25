package com.dkmo.living_chatting.infrastructure.gateways;

import com.dkmo.living_chatting.domain.model.FileReference;
import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;

public class UserEntityMapper {
UserEntity toEntity(User userDomainObject){
   return new UserEntity(userDomainObject.nome(),   userDomainObject.email(), userDomainObject.senha(),userDomainObject.usuario());
  }   
  User toDomain(UserEntity userEntity){
    User user = User.create(userEntity.getName(), userEntity.getEmail(), userEntity.getSenha(), userEntity.getUsuario());
    if(userEntity.getPhotoProfile()!=null){
    user.definePhotoProfile(new FileReference(userEntity.getPhotoProfile()));
    }
    return user;
  }
}
  
