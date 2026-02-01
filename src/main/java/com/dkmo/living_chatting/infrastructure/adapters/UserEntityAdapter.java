package com.dkmo.living_chatting.infrastructure.adapters;

import com.dkmo.living_chatting.domain.model.FileReference;
import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;

public class UserEntityAdapter {
public static User toUser(UserEntity user){
    User userCreated = User.create(user.getName(),null, user.getEmail(), user.getUsuario());
    FileReference fileReference = new FileReference(user.getPhotoProfile());
    userCreated.definePhotoProfile(fileReference);
    return userCreated;
  }    
}
