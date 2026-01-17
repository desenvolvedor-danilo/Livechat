package com.dkmo.living_chatting.controller;

import java.util.ArrayList;
import java.util.List;

import com.dkmo.living_chatting.domain.model.User;

public class UserDTOMapper {
CreateUserResponse toResponse(User user){
    return new CreateUserResponse(user.email(),user.nome());
  } 
List<CreateUserResponse> listToResponse(List<User> user){
    List<CreateUserResponse> createUserResponse = new ArrayList<>();
    user.forEach(usuario->{
      CreateUserResponse response = new CreateUserResponse(usuario.email(), usuario.nome());
      createUserResponse.add(response);
    });
    return createUserResponse;
  }
User toUser(CreateUserRequest request){
    return new User(request.nome(),request.email(),request.password(),request.username());
  }
}
