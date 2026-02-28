package com.dkmo.living_chatting.infrastructure.adapters;

import com.dkmo.living_chatting.domain.model.FileReference;
import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;

public class UserEntityAdapter {
public static User toUser(UserEntity user){
    User userCreated = User.create(user.getName(), user.getEmail(),user.getSenha(), user.getUsuario());
    if(user.getPhotoProfile()!=null){
    FileReference fileReference = new FileReference(user.getPhotoProfile());
    userCreated.definePhotoProfile(fileReference);
    }
    userCreated.defineToken(user.getFcmToken());
    
    return userCreated;
  }   

public static UserEntity toUserEntity(User user){
    UserEntity userCreated = new UserEntity(user.nome(),user.email(),user.senha(),user.usuario());
    userCreated.setPhotoProfile(user.getFileReference().url());
    return userCreated;
  }    
}
