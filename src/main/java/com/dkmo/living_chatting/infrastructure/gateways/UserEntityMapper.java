package com.dkmo.living_chatting.infrastructure.gateways;

import com.dkmo.living_chatting.domain.model.FileReference;
import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;

public class UserEntityMapper {
UserEntity toEntity(User userDomainObject){
   UserEntity userEntity = new UserEntity(userDomainObject.nome(),   userDomainObject.email(), userDomainObject.senha(),userDomainObject.usuario());
    userEntity.setId(userDomainObject.id().toString());
    return userEntity;
  }   
  User toDomain(UserEntity userEntity){
    User user = User.create(userEntity.getName(), userEntity.getEmail(), userEntity.getSenha(), userEntity.getUsuario());
    if(userEntity.getPhotoProfile()!=null){
    user.definePhotoProfile(new FileReference(userEntity.getPhotoProfile()));
    }
    user.defineToken(userEntity.getFcmToken());
    return user;
  }
}
  
